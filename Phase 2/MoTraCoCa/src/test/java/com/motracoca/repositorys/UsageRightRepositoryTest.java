package com.motracoca.repositorys;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.store.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UsageRightRepositoryTest {
    @Autowired
    private UsageRightRepository usageRightRepository;

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
    public void init(){
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
    @AfterEach
    public void cleanup() {
        usageRightRepository.deleteAll();

    }

    @DisplayName("should store a UsageRight")
    @Test
    public void storeUsageRightTest() {

       /* ServiceEntity se1 = new ServiceEntity();
        ServiceEntity se2 = new ServiceEntity();
        ServiceEntity se3 = new ServiceEntity();
        ServiceEntity se4 = new ServiceEntity();

        List<ServiceEntity> offeredServices = new ArrayList<>();
        offeredServices.add(se1);
        offeredServices.add(se2);
        offeredServices.add(se3);
        offeredServices.add(se4);

        List<ServiceEntity> includedServices = new ArrayList<>();
        includedServices.add(se1);
        includedServices.add(se2);
        includedServices.add(se4);

        ProductEntity pe1 = new ProductEntity();
        pe1.setIncludedServices(includedServices);
        pe1.setPrice(13.20);

        CustomerEntity c1 = new CustomerEntity();

        VehicleEntity v1 = new VehicleEntity();
        v1.setOwner(c1);
        v1.setServiceEntityList(offeredServices);

        ProductConfigurationEntity pce1 = new ProductConfigurationEntity();
        pce1.setProductEntity(pe1);
        pce1.setDuration(6);
        List<ProductConfigurationEntity> pceList = new ArrayList<>();
        pceList.add(pce1);*/

        OrderEntity o1 = new OrderEntity();
        o1.setVehicleEntity(safedVehicleEntity);
        o1.setCustomerEntity(safedCustomerEntitity);

        List<ProductConfigurationEntity> pceList = new ArrayList<>();
        for (ProductConfiguration pc : articleNumberDurationList) {
            ProductConfigurationEntity pce = ProductConfigurationStore.convertToProductConfigurationEntity(pc);
            pceList.add(pce);
        }

        o1.setProducts(pceList);

        OrderEntity safedOrder = os.saveOrder(OrderStore.convertToOrder(o1));

        ServiceEntity coveredService = ServiceStore.convertToServiceEntity(safedService1);


        UsageRightEntity ure = new UsageRightEntity();
        ure.setCoveredService(coveredService);
        ure.setCoveredVehicle(safedVehicleEntity);
        ure.setCoveredCustomer(safedCustomerEntitity);
        ure.setFromProduct(ProductStore.convertToProductEntity(safedProduct1));
        ure.setFromOrder(safedOrder);

        usageRightRepository.save(ure);

        List<UsageRightEntity> savedUsageRight = usageRightRepository.findAll();

        assertThat(savedUsageRight).isNotNull();
        assertThat(savedUsageRight.get(0).getFromOrder().getProducts().get(0).getProductEntity().getPrice()).isEqualTo(15.99);

    }

}
