package com.motracoca.repositorys;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.store.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductEntityRepositoryTest {
    /*private long ID = 0L;
    private double PRICE = 420.0;
    private ServiceEntity SERVICE1 = new ServiceEntity();
    private ServiceEntity SERVICE2 = new ServiceEntity();
    private long ARTICLENUMBER = 8150815L;*/
    @Autowired
    ProductRepository productRepository;


    @Autowired
    private CustomerStore cs;
    @Autowired
    private OrderStore os;
    @Autowired
    private ProductStore ps;
    @Autowired
    private VehicleStore vs;
    @Autowired
    private ServiceStore ss;

    private List<ProductConfiguration> articleNumberDurationList;
    private Vehicle v;
    private VehicleEntity safedVehicleEntity;

    private CustomerEntity safedCustomerEntitity;
    private Service safedService1;
    private Service safedService2;
    private Service safedService3;
    private Product safedProduct1;
    private Product safedProduct2;



    @BeforeEach
    public void init() {

       /* SERVICE1.setName("SERVICE1");
        SERVICE2.setName("SERVICE2");

        SERVICE1.setId(123123L);
        SERVICE2.setId(123124L);
        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(ID);
        productEntity.setPrice(PRICE);

        productEntity.setArticleNumber(ARTICLENUMBER);
        productEntity.setIncludedServices(List.of(SERVICE1, SERVICE2));

        productRepository.save(productEntity);*/


        Price pricePerMonth1 = new Price(15.99);
        Price pricePerMonth2 = new Price(12.99);
        Service s1 = new Service(0L, "service1");
        Service s2 = new Service(0L, "service2");
        Service s3 = new Service(0L, "service3");

        safedService1 = ss.safeService(s1);
        safedService2 = ss.safeService(s2);
        safedService3 = ss.safeService(s3);


        List<Service> serviceList1 = new ArrayList<>();
        List<Service> serviceList2 = new ArrayList<>();
        serviceList1.add(safedService1);
        serviceList1.add(safedService2);
        serviceList1.add(safedService3);
        serviceList2.add(safedService1);
        serviceList2.add(safedService3);

        ArticleNumber an1 = new ArticleNumber(123L);
        ArticleNumber an2 = new ArticleNumber(456L);
        Product p1 = new Product(0L, an1, pricePerMonth1, serviceList1);
        Product p2 = new Product(0L, an2, pricePerMonth2, serviceList2);

        safedProduct1 = ps.saveProduct(p1);
        safedProduct2 = ps.saveProduct(p2);

        ProductConfiguration  pc1 = new ProductConfiguration(0L, safedProduct1, 3);
        ProductConfiguration  pc2 = new ProductConfiguration(0L, safedProduct2, 6);

        articleNumberDurationList = new ArrayList<>();
        articleNumberDurationList.add(pc1);
        articleNumberDurationList.add(pc2);

        Customer c = new Customer(0L, "payment");
        Customer safedCustomer = cs.saveCustomer(c);
        safedCustomerEntitity = CustomerStore.convertToCustomerEntity(safedCustomer);

        Vin vin = new Vin("vin123");
        v = new Vehicle(0L, vin, safedCustomer, serviceList1);

        safedVehicleEntity = vs.saveVehicle(v);

    }

    @Test
    public void productEntityTest() {


        List<ProductEntity> productEntityList = productRepository.findAll();

        assertThat(productEntityList.size()).isNotNull();
        assertThat(productEntityList.size()).isEqualTo(2);

        assertThat(productEntityList.get(0).getIncludedServices().size()).isEqualTo(3);

        assertThat(productEntityList.get(0).getArticleNumber()).isEqualTo(123L);

        assertThat(productEntityList.get(0).getIncludedServices().get(1).getName()).isEqualTo("service2");
    }


}
