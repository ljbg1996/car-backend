package com.motracoca.services;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.repositorys.CustomerRepository;
import com.motracoca.repositorys.UsageRightRepository;
import com.motracoca.store.UsageRightStore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ActiveServiceServiceTest {

    @Autowired
    private UsageRightRepository urr;
    @Autowired
    private UsageRightStore urs = new UsageRightStore();

    private VehicleEntity ve1;

    @BeforeEach
    void init() {

        UsageRightEntity ure1 = new UsageRightEntity();
        UsageRightEntity ure2 = new UsageRightEntity();
        UsageRightEntity ure3 = new UsageRightEntity();
        UsageRightEntity ure4 = new UsageRightEntity();
        UsageRightEntity ure5 = new UsageRightEntity();

        ServiceEntity se1 = new ServiceEntity();
        se1.setName("Service1");
        ServiceEntity se2 = new ServiceEntity();
        se2.setName("Service2");
        ServiceEntity se3 = new ServiceEntity();
        se3.setName("Service3");
        ServiceEntity se4 = new ServiceEntity();
        se4.setName("Service4");
        ServiceEntity se5 = new ServiceEntity();
        se5.setName("Service5");

        CustomerEntity ce1 = new CustomerEntity();
        ce1.setPaymentInfo("paypal");
        CustomerEntity ce2 = new CustomerEntity();
        ce2.setPaymentInfo("transfer");

        ve1 = new VehicleEntity();
        ve1.setVin("vin123");
        ve1.setOwner(ce1);
        List<ServiceEntity> offeredServiceList = new ArrayList<>();
        offeredServiceList.add(se1);
        offeredServiceList.add(se2);
        offeredServiceList.add(se3);
        offeredServiceList.add(se4);
        offeredServiceList.add(se5);
        VehicleEntity ve2 = new VehicleEntity();
        ve2.setVin("vin456");
        ve1.setOwner(ce2);
        List<ServiceEntity> offeredServiceList2 = new ArrayList<>();
        offeredServiceList2.add(se1);
        offeredServiceList2.add(se2);
        offeredServiceList2.add(se3);

        ProductEntity pe1 = new ProductEntity();
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
        pce2.setDuration(6);

        OrderEntity oe1 = new OrderEntity();
        oe1.setPayed(true);
        LocalDate paymentDate = LocalDate.of(2023, 5, 29);
        oe1.setPaymentDate(paymentDate);
        oe1.setCanceled(false);
        oe1.setCancellationDate(null);
        oe1.setVehicleEntity(ve1);
        oe1.setCustomerEntity(ce1);
        oe1.setTotalPrice(38.97);
        List<ProductConfigurationEntity> pceList = new ArrayList<>();
        pceList.add(pce1);
        oe1.setProducts(pceList);
        oe1.setDate(LocalDate.of(2023, 5, 29));

        OrderEntity oe2 = new OrderEntity();
        oe2.setPayed(true);
        LocalDate paymentDate1 = LocalDate.of(2023, 4, 25);
        oe2.setPaymentDate(paymentDate1);
        oe2.setCanceled(false);
        oe2.setCancellationDate(null);
        oe2.setVehicleEntity(ve2);
        oe2.setCustomerEntity(ce2);
        oe2.setTotalPrice(41.94);
        List<ProductConfigurationEntity> pceList1 = new ArrayList<>();
        pceList1.add(pce2);
        oe2.setProducts(pceList1);
        oe2.setDate(LocalDate.of(2023, 4, 23));

        ure1.setStartDate(LocalDate.of(2023, 5, 29));
        ure1.setEndDate(LocalDate.of(2023, 8, 29));
        ure1.setCoveredService(se1);
        ure1.setCoveredVehicle(ve1);
        ure1.setCoveredCustomer(ce1);
        ure1.setFromProduct(pe1);
        ure1.setFromOrder(oe1);

        ure3.setStartDate(LocalDate.of(2023, 5, 29));
        ure3.setEndDate(LocalDate.of(2023, 8, 29));
        ure3.setCoveredService(se3);
        ure3.setCoveredVehicle(ve1);
        ure3.setCoveredCustomer(ce1);
        ure3.setFromProduct(pe1);
        ure3.setFromOrder(oe1);

        ure5.setStartDate(LocalDate.of(2023, 5, 29));
        ure5.setEndDate(LocalDate.of(2023, 8, 29));
        ure5.setCoveredService(se5);
        ure5.setCoveredVehicle(ve1);
        ure5.setCoveredCustomer(ce1);
        ure5.setFromProduct(pe1);
        ure5.setFromOrder(oe1);

        ure2.setStartDate(LocalDate.of(2023, 4, 25));
        ure2.setEndDate(LocalDate.of(2023, 10, 29));
        ure2.setCoveredService(se2);
        ure2.setCoveredVehicle(ve2);
        ure2.setCoveredCustomer(ce2);
        ure2.setFromProduct(pe2);
        ure2.setFromOrder(oe2);

        ure4.setStartDate(LocalDate.of(2023, 4, 25));
        ure4.setEndDate(LocalDate.of(2023, 10, 29));
        ure4.setCoveredService(se4);
        ure4.setCoveredVehicle(ve2);
        ure4.setCoveredCustomer(ce2);
        ure4.setFromProduct(pe2);
        ure4.setFromOrder(oe2);

        urr.save(ure1);
        urr.save(ure2);
        urr.save(ure3);
        urr.save(ure4);
        urr.save(ure5);

    }


    @Test
    @DisplayName("Should display the active services of a user ")
    public void getActiveServicesTest() {

        List<ServiceEntity> serviceEntitiesWithVin = urs.getUsageRightEntitiesByVin(ve1);

        assertThat(serviceEntitiesWithVin.size()).isEqualTo(3);

    }
/*        @Test
    public void extractServicesTest(){
        //given
        Service s1 = new Service(SERVICEID1,SERVICENAME1);
        Service s2 = new Service(SERVICEID2,SERVICENAME2);

        Product product = new Product(PRODUCTID1, new ArticleNumber(12345678), new Price(5),List.of(s1, s2));

//        UsageRight usageRight1 = new UsageRight(USAGERIGHTID,LocalDate.now(),LocalDate.now(),product);

//        Vehicle v1 = new Vehicle(VEHICLEID,VIN,List.of(s1,s2),List.of(usageRight1));

//        Customer customer = new Customer()
//        customerRepository.save(customer);

        ActiveServiceService activeServiceService = new ActiveServiceService();

        //when
//        List<Service> activeServices = activeServiceService.extractServices(v1,CUSTOMERID);

        //then
//            Assertions.assertThat(activeServices).size().isEqualTo(2);
        }*/

}
