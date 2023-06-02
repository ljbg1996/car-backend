package com.motracoca.controller;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.Customer;
import com.motracoca.model.Vehicle;

import com.motracoca.model.Service;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.VehicleRepository;
import com.motracoca.store.VehicleStore;
import io.swagger.v3.oas.annotations.Operation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.DirtiesContext;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


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


    private String url;
    private String customer;
    private String vin;

    @BeforeEach
    void init() {
        customer = "1337";
        vin = "XYZ420";
        url = "http://localhost:" + port + "/" + vin + "/" + customer + "/";

        Customer customerModel = new Customer(132222237L, "Paypal");

        Vehicle vehicleModel = new Vehicle(
                0L,
                new Vin(vin),
                customerModel,
                List.of(new Service(0L, "TestService")));

        vehicleRepository.save(VehicleStore.convertToVehicleEntity(vehicleModel));
    }

    @AfterEach
    public void cleanUp() {
        vehicleRepository.deleteAll();
    }

    @Test
    void getActiveServicesTest() {
        ResponseEntity<List<Service>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<List<Service>>() {
                });

        List<Service> services = response.getBody();

        Assertions.assertThat(services.get(0).name()).isEqualTo("TestService");
    }

    @AfterEach
    void cleanup() {
        vehicleRepository.deleteAll();
    }
}

