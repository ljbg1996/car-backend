package com.motracoca.services;

import com.motracoca.controller.OrderRESTController;
import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.*;
import com.motracoca.repositorys.CustomerRepository;
import com.motracoca.store.ProductConfigurationStore;
import com.motracoca.store.ProductStore;
import com.motracoca.store.VehicleStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class OrderServiceTest {

    private OrderService orderService;


    private VehicleStore vs;

    @DisplayName("should place a order and update the customer")
    @Test
    public void placeOrder(){

        Price pricePerMonth1 = new Price(15.99);
        Price pricePerMonth2 = new Price(12.99);
        Service s1 = new Service(1L, "service1");
        Service s2 = new Service(2L, "service2");
        Service s3 = new Service(3L, "service3");
        List<Service> serviceList1 = new ArrayList<>();
        List<Service> serviceList2 = new ArrayList<>();
        serviceList1.add(s1);
        serviceList1.add(s2);
        serviceList1.add(s3);
        serviceList2.add(s1);
        serviceList2.add(s3);
        ArticleNumber an1 = new ArticleNumber(123L);
        ArticleNumber an2 = new ArticleNumber(456L);
        Product p1 = new Product(1L, an1, pricePerMonth1, serviceList1);
        Product p2 = new Product(2L, an2, pricePerMonth2, serviceList2);

        ProductConfiguration  pc1 = new ProductConfiguration(1L, p1, 3);
        ProductConfiguration  pc2 = new ProductConfiguration(2L, p2, 6);

        List<ProductConfiguration> articleNumberDurationList = new ArrayList<>();
        articleNumberDurationList.add(pc1);
        articleNumberDurationList.add(pc2);

        Customer c = new Customer(1L, "payment");
        Vin vin = new Vin("vin123");
        Vehicle v = new Vehicle(1L, vin, c, serviceList1);

         vs.saveVehicle(v);

        Order savedOrder = orderService.buy(articleNumberDurationList, vin.vin());

        assertThat(savedOrder.getProducts().size()).isEqualTo(2);
        assertThat(savedOrder.getTotalPrice().price()).isEqualTo(125.91);
        assertThat(savedOrder.getProducts().get(0).product().getIncludedServices().size()).isEqualTo(3);
        assertThat(savedOrder.getProducts().get(1).product().getIncludedServices().size()).isEqualTo(2);
        assertThat(savedOrder.getProducts().get(0).duration()).isEqualTo(3);
        assertThat(savedOrder.getProducts().get(1).duration()).isEqualTo(6);
    }








}

