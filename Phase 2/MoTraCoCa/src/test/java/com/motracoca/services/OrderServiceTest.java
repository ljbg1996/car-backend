package com.motracoca.services;

import com.motracoca.controller.OrderRESTController;
import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.repositorys.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class OrderServiceTest {

    private OrderService orderService;

    @Autowired
    private CustomerRepository cr;

    @DisplayName("should place a order and update the customer")
    @Test
    public void placeOrder(){

        VehicleEntity v1 = new VehicleEntity();
        VehicleEntity v2 = new VehicleEntity();
        CustomerEntity c1 = new CustomerEntity();
        c1.getVehicleEntityList().add(v1);
        c1.getVehicleEntityList().add(v2);

        cr.save(c1);

        orderService.buy();
    }








}

