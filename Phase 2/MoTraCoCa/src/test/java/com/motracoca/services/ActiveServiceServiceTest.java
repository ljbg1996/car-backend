package com.motracoca.services;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.model.*;
import com.motracoca.repositorys.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class ActiveServiceServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    private final Vin VIN = new Vin("VIN1234567890");
    private final long VEHICLEID = 23456789;
    private final long CUSTOMERID = 12345678;
    private final long SERVICEID1 = 45678901;
    private final long SERVICEID2 = 56789012;
    private final long PRODUCTID1 = 67890123;
    private final String SERVICENAME1 = "name1";
    private final String SERVICENAME2 = "name2";
    private final long USAGERIGHTID = 901234567;

//    private final ArticleNumber articleNumber;
//    private final Price price;
//    private final List<Service> includedServices;

    @BeforeEach
    void init() {

//        v1.setVin(VIN);
        CustomerEntity c1 = new CustomerEntity();
        c1.setId(CUSTOMERID);




//        c1.setVehicleEntityList(List.of(v1));
        customerRepository.save(c1);
    }


    @Test
    public void getActiveServicesTest() {
        ActiveServiceService activeServiceService = new ActiveServiceService();

        List<Service> result = activeServiceService.getActiveServices(VIN, CUSTOMERID);

        Assertions.assertThat(result).isNotNull();
//        customerRepository.save(c1);
    }
        @Test
    public void extractServicesTest(){
        //given
        Service s1 = new Service(SERVICEID1,SERVICENAME1);
        Service s2 = new Service(SERVICEID2,SERVICENAME2);

        Product product = new ProductBuilder().setId(PRODUCTID1).setArticleNumber(new ArticleNumber(12345678)).setPrice(new Price(5)).setIncludedServices(List.of(s1, s2)).createProduct();
        Price p1 = new Price(5);
        p1.

        UsageRight usageRight1 = new UsageRight(USAGERIGHTID,LocalDate.now(),LocalDate.now(),product);

        Vehicle v1 = new Vehicle(VEHICLEID,VIN,List.of(s1,s2),List.of(usageRight1));

        ActiveServiceService activeServiceService = new ActiveServiceService();

        //when
        List<Service> activeServices = activeServiceService.extractServices(v1,CUSTOMERID);

        //then
            Assertions.assertThat(activeServices).size().isEqualTo(2);
        }

}
