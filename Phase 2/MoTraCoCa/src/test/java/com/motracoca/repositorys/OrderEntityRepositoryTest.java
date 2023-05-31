package com.motracoca.repositorys;

import static org.assertj.core.api.Assertions.assertThat;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.store.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderEntityRepositoryTest {


    @Autowired
    private OrderRepository or;
    @Autowired
    private ServiceRepository sr;
    @Autowired
    private ProductRepository pr;

    @Autowired
    private VehicleRepository vr;

    @Autowired
    private CustomerRepository cr;

    @Autowired
    private CustomerStore cs;
    @Autowired
    private ProductStore ps;
    @Autowired
    private VehicleStore vs;
    @Autowired
    private ServiceStore ss;

    List<ProductConfiguration> articleNumberDurationList;
    Vehicle v;
    VehicleEntity safedVehicleEntity;

    @BeforeEach
    public void init(){
        Price pricePerMonth1 = new Price(15.99);
        Price pricePerMonth2 = new Price(12.99);
        Service s1 = new Service(0L, "service1");
        Service s2 = new Service(0L, "service2");
        Service s3 = new Service(0L, "service3");

        Service safedService1 = ss.safeService(s1);
        Service safedService2 = ss.safeService(s2);
        Service safedService3 = ss.safeService(s3);


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

        Product safedProduct1 = ps.saveProduct(p1);
        Product safedProduct2 = ps.saveProduct(p2);

        ProductConfiguration  pc1 = new ProductConfiguration(0L, safedProduct1, 3);
        ProductConfiguration  pc2 = new ProductConfiguration(0L, safedProduct2, 6);

        articleNumberDurationList = new ArrayList<>();
        articleNumberDurationList.add(pc1);
        articleNumberDurationList.add(pc2);

        Customer c = new Customer(0L, "payment");
        Vin vin = new Vin("vin123");
        v = new Vehicle(0L, vin, c, serviceList1);

        cs.saveCustomer(c);
        safedVehicleEntity = vs.saveVehicle(v);

    }


    @DisplayName("should store a order")
    @Test
    public void storeOrderTest() {

        /*ServiceEntity se1 = new ServiceEntity();
        ServiceEntity se2 = new ServiceEntity();
        ServiceEntity savedServiceEntity1 = sr.save(se1);
        ServiceEntity savedServiceEntity2 = sr.save(se2);

        List<ServiceEntity> serviceList = new ArrayList<>();
        serviceList.add(savedServiceEntity1);
        serviceList.add(savedServiceEntity2);

        ProductEntity pe1 = new ProductEntity();
        pe1.setIncludedServices(serviceList);

        ProductEntity safedProductEntity = pr.save(pe1);

        ProductConfigurationEntity pce1 = new ProductConfigurationEntity();
        pce1.setProductEntity(safedProductEntity);
        pce1.setDuration(6);

        List<ProductConfigurationEntity> productConfigurationEntityList1 = new ArrayList<>();
        productConfigurationEntityList1.add(pce1);

        VehicleEntity ve1 = new VehicleEntity();
        ve1.setServiceEntityList(serviceList);

        VehicleEntity safedVehicleEntity = vr.save(ve1);*/

        OrderEntity order1 = new OrderEntity();
        List<ProductConfigurationEntity> pceList = new ArrayList<>();
        for (ProductConfiguration pc : articleNumberDurationList) {
            ProductConfigurationEntity pce = ProductConfigurationStore.convertToProductConfigurationEntity(pc);
            pceList.add(pce);

        }
        order1.setProducts(pceList);
        order1.setPayed(true);
        order1.setVehicleEntity(safedVehicleEntity);
        order1.setTotalPrice(20.99);
        LocalDate date = LocalDate.now();
        order1.setDate(date);



        or.save(order1);

        final List<OrderEntity> orderList = or.findAll();

        assertThat(orderList.size()).isNotNull();
        assertThat(orderList.size()).isEqualTo(1);

        assertThat(orderList.get(0).getProducts().size()).isEqualTo(2);

        assertThat(orderList.get(0).isPayed()).isTrue();

        assertThat(orderList.get(0).getTotalPrice()).isEqualTo(20.99);



    }




}
