package com.motracoca.controller;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.Customer;
import com.motracoca.model.Vehicle;

import com.motracoca.model.Service;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.CustomerRepository;
import com.motracoca.repositorys.ServiceRepository;
import com.motracoca.repositorys.VehicleRepository;
import com.motracoca.store.CustomerStore;
import com.motracoca.store.ServiceStore;
import com.motracoca.store.VehicleStore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;


import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ActiveServiceRESTControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    private String url;
    private String customer;
    private String vin;

    @BeforeEach
    void init() {

        vin = "XYZ420";

        Customer customerModel = new Customer(0L, "Paypal");
        CustomerEntity customerEntity = CustomerStore.convertToCustomerEntity(customerModel);

        Customer customerDB = CustomerStore.convertToCustomer(customerRepository.save(customerEntity));

        Service serviceModel = new Service(0L,"Test-Service");
        ServiceEntity serviceEntity = ServiceStore.convertToServiceEntity(serviceModel);

        Service serviceDB = ServiceStore.convertToService(serviceRepository.save(serviceEntity));


        Vehicle vehicleModel = new Vehicle(
                0L,
                new Vin(vin),
                customerDB,
                List.of(serviceDB));


        vehicleRepository.save(VehicleStore.convertToVehicleEntity(vehicleModel));

        customer = String.valueOf(customerDB.getId());

        url = "http://localhost:" + port + "/" + vin + "/" + customer + "/";

    }

    @AfterEach
    public void cleanUp() {
        vehicleRepository.deleteAll();
    }

    @Test
    void getActiveServicesTest() {
        ResponseEntity<ServiceList> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                ServiceList.class);

        List<Service> services = response.getBody().serviceList();

        Assertions.assertThat(services.get(0).name()).isEqualTo("TestService");
    }

    @AfterEach
    void cleanup() {
        vehicleRepository.deleteAll();
    }
}

