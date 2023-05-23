package com.motracoca.services;

import com.motracoca.controller.OrderRESTController;
import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.repositorys.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        List<OrderEntity> orderList1 = c1.getOrderEntityList();

        cr.save(c1);



        orderService.buy();

        CustomerEntity customerFromDb = cr.getById(c1.getId());
        List<OrderEntity> orderList2 = customerFromDb.getOrderEntityList();
        OrderEntity orderFromCustomerC1 = customerFromDb.getOrderEntityList().get(customerFromDb.getOrderEntityList().size()-1);

        assertThat(orderList1.size()+1).isEqualTo(orderList2);
        assertThat(orderFromCustomerC1).isNotNull();
    }








}

