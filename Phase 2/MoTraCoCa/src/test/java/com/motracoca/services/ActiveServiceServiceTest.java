package com.motracoca.services;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.repositorys.CustomerRepository;
import com.motracoca.repositorys.UsageRightRepository;
import com.motracoca.store.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ActiveServiceServiceTest {

    @Autowired
    private UsageRightRepository urr;
    @Autowired
    private UsageRightStore urs = new UsageRightStore();
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
    private VehicleEntity safedVehicle1;
    private VehicleEntity safedVehicle2;
    @Autowired
    private ActiveServiceService activeServiceService;

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

        /*Vin vin = new Vin("vin123");
        v = new Vehicle(0L, vin, safedCustomer, serviceList1);

        safedVehicleEntity = vs.saveVehicle(v);*/


        UsageRightEntity ure1 = new UsageRightEntity();
        UsageRightEntity ure2 = new UsageRightEntity();
        UsageRightEntity ure3 = new UsageRightEntity();
        UsageRightEntity ure4 = new UsageRightEntity();
        UsageRightEntity ure5 = new UsageRightEntity();


        CustomerEntity ce1 = new CustomerEntity();
        ce1.setPaymentInfo("paypal");
        CustomerEntity safedCustomerEntity1 = CustomerStore.convertToCustomerEntity(cs.saveCustomer(CustomerStore.convertToCustomer(ce1)));
        CustomerEntity ce2 = new CustomerEntity();
        ce2.setPaymentInfo("transfer");
        CustomerEntity safedCustomerEntity2 = CustomerStore.convertToCustomerEntity(cs.saveCustomer(CustomerStore.convertToCustomer(ce2)));

        VehicleEntity ve1 = new VehicleEntity();
        ve1.setVin("vin123");
        ve1.setOwner(safedCustomerEntity1);
        List<ServiceEntity> offeredServiceList = new ArrayList<>();
        offeredServiceList.add(ServiceStore.convertToServiceEntity(safedService1));
        offeredServiceList.add(ServiceStore.convertToServiceEntity(safedService3));
        ve1.setServiceEntityList(offeredServiceList);

        safedVehicle1 = vs.saveVehicle(VehicleStore.convertToVehicle(ve1));

        VehicleEntity ve2 = new VehicleEntity();
        ve2.setVin("vin456");
        ve2.setOwner(safedCustomerEntity2);
        List<ServiceEntity> offeredServiceList2 = new ArrayList<>();
        offeredServiceList2.add(ServiceStore.convertToServiceEntity(safedService1));
        offeredServiceList2.add(ServiceStore.convertToServiceEntity(safedService2));
        offeredServiceList2.add(ServiceStore.convertToServiceEntity(safedService3));
        ve2.setServiceEntityList(offeredServiceList2);

        safedVehicle2 = vs.saveVehicle(VehicleStore.convertToVehicle(ve2));

        /*ProductEntity pe1 = new ProductEntity();
        pe1.setArticleNumber(12345L);
        pe1.setPrice(12.99);
        List<ServiceEntity> includedServices1 = new ArrayList<>();
        includedServices1.add(se1);
        includedServices1.add(se3);
        includedServices1.add(se5);
        ProductEntity pe2 = new ProductEntity();
        pe2.setArticleNumber(6789L);
        pe2.setPrice(6.99);
        List<ServiceEntity> includedServices2 = new ArrayList<>();
        includedServices2.add(se2);
        includedServices2.add(se4);

        ProductConfigurationEntity pce1 = new ProductConfigurationEntity();
        pce1.setProductEntity(pe1);
        pce1.setDuration(3);
        ProductConfigurationEntity pce2 = new ProductConfigurationEntity();
        pce2.setProductEntity(pe2);
        pce2.setDuration(6);*/

        OrderEntity oe1 = new OrderEntity();
        oe1.setPayed(true);
        LocalDate paymentDate = LocalDate.of(2023, 5, 29);
        oe1.setPaymentDate(paymentDate);
        oe1.setCanceled(false);
        oe1.setCancellationDate(null);
        oe1.setVehicleEntity(safedVehicle1);
        oe1.setCustomerEntity(safedCustomerEntity1);
        oe1.setTotalPrice(38.97);
        List<ProductConfigurationEntity> pceList1 = new ArrayList<>();
        articleNumberDurationList.remove(pc2);
        for (ProductConfiguration pc : articleNumberDurationList) {
            ProductConfigurationEntity pce = ProductConfigurationStore.convertToProductConfigurationEntity(pc);
            pceList1.add(pce);
        }
        oe1.setProducts(pceList1);
        oe1.setDate(LocalDate.of(2023, 5, 29));

        OrderEntity safedOrder1 = os.saveOrder(OrderStore.convertToOrder(oe1));

        OrderEntity oe2 = new OrderEntity();
        oe2.setPayed(true);
        LocalDate paymentDate1 = LocalDate.of(2023, 4, 25);
        oe2.setPaymentDate(paymentDate1);
        oe2.setCanceled(false);
        oe2.setCancellationDate(null);
        oe2.setVehicleEntity(safedVehicle2);
        oe2.setCustomerEntity(safedCustomerEntity2);
        oe2.setTotalPrice(41.94);
        List<ProductConfigurationEntity> pceList2 = new ArrayList<>();
        articleNumberDurationList.remove(pc1);
        articleNumberDurationList.add(pc2);
        for (ProductConfiguration pc : articleNumberDurationList) {
            ProductConfigurationEntity pce = ProductConfigurationStore.convertToProductConfigurationEntity(pc);
            pceList2.add(pce);
        }
        oe2.setProducts(pceList2);
        oe2.setDate(LocalDate.of(2023, 4, 23));

        OrderEntity safedOrder2 = os.saveOrder(OrderStore.convertToOrder(oe2));

        ure1.setStartDate(LocalDate.of(2023, 5, 29));
        ure1.setEndDate(LocalDate.of(2023, 8, 29));
        ure1.setCoveredService(ServiceStore.convertToServiceEntity(safedService1));
        ure1.setCoveredVehicle(safedVehicle1);
        ure1.setCoveredCustomer(safedCustomerEntity1);
        ure1.setFromProduct(ProductStore.convertToProductEntity(safedProduct1));
        ure1.setFromOrder(safedOrder1);

        ure3.setStartDate(LocalDate.of(2023, 5, 29));
        ure3.setEndDate(LocalDate.of(2023, 8, 29));
        ure3.setCoveredService(ServiceStore.convertToServiceEntity(safedService2));
        ure3.setCoveredVehicle(safedVehicle1);
        ure3.setCoveredCustomer(safedCustomerEntity1);
        ure3.setFromProduct(ProductStore.convertToProductEntity(safedProduct1));
        ure3.setFromOrder(safedOrder1);

        ure5.setStartDate(LocalDate.of(2023, 5, 29));
        ure5.setEndDate(LocalDate.of(2023, 8, 29));
        ure5.setCoveredService(ServiceStore.convertToServiceEntity(safedService3));
        ure5.setCoveredVehicle(safedVehicle1);
        ure5.setCoveredCustomer(safedCustomerEntity1);
        ure5.setFromProduct(ProductStore.convertToProductEntity(safedProduct1));
        ure5.setFromOrder(safedOrder1);

        ure2.setStartDate(LocalDate.of(2023, 4, 25));
        ure2.setEndDate(LocalDate.of(2023, 10, 29));
        ure2.setCoveredService(ServiceStore.convertToServiceEntity(safedService1));
        ure2.setCoveredVehicle(safedVehicle2);
        ure2.setCoveredCustomer(safedCustomerEntity2);
        ure2.setFromProduct(ProductStore.convertToProductEntity(safedProduct2));
        ure2.setFromOrder(safedOrder2);

        ure4.setStartDate(LocalDate.of(2023, 4, 25));
        ure4.setEndDate(LocalDate.of(2023, 10, 29));
        ure4.setCoveredService(ServiceStore.convertToServiceEntity(safedService3));
        ure4.setCoveredVehicle(safedVehicle2);
        ure4.setCoveredCustomer(safedCustomerEntity2);
        ure4.setFromProduct(ProductStore.convertToProductEntity(safedProduct2));
        ure4.setFromOrder(safedOrder2);

        urr.save(ure1);
        urr.save(ure2);
        urr.save(ure3);
        urr.save(ure4);
        urr.save(ure5);

    }



    @Test
    @DisplayName("Should display the active services of a user ")
    public void getActiveServicesTest() {

        List<Service> servicesWithVin1 = activeServiceService.getActiveServices(VehicleStore.convertToVehicle(safedVehicle1).getVin());
        List<Service> servicesWithVin2 = activeServiceService.getActiveServices(VehicleStore.convertToVehicle(safedVehicle2).getVin());

        assertThat(servicesWithVin1.size()).isEqualTo(3);
        assertThat(servicesWithVin2.size()).isEqualTo(2);

    }
    @AfterEach
    public void cleanUp(){
        urr.deleteAll();
    }


}
