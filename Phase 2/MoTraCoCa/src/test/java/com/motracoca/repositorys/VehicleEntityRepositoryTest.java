package com.motracoca.repositorys;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.*;
import com.motracoca.store.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VehicleEntityRepositoryTest {

    @Autowired
    private VehicleRepository vr;

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
    public void cleanUp(){
        vr.deleteAll();
    }


    @DisplayName("should store a customer with vehicleList")
    @Test
    public void storeCustomerVehicle() {

        /*ServiceEntity se1 = new ServiceEntity();
        ServiceEntity se2 = new ServiceEntity();

        List<ServiceEntity> serviceList = new ArrayList<>();
        serviceList.add(se1);
        serviceList.add(se2);

        CustomerEntity c1 = new CustomerEntity();
        c1.setPaymentInfo("Paypal");*/

        VehicleEntity v1 = new VehicleEntity();
        v1.setVin("Vin123");
        v1.setOwner(safedCustomerEntitity);

        List<ServiceEntity> offeredServiceList = new ArrayList<>();
        offeredServiceList.add(ServiceStore.convertToServiceEntity(safedService1));
        offeredServiceList.add(ServiceStore.convertToServiceEntity(safedService2));
        offeredServiceList.add(ServiceStore.convertToServiceEntity(safedService3));

        v1.setServiceEntityList(offeredServiceList);

        vr.save(v1);

        List<VehicleEntity> savedVehicle = vr.findAll();

        assertThat(savedVehicle).isNotNull();







    }
}
