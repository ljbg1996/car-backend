package com.motracoca.services;


import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.*;

import com.motracoca.store.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private CustomerStore cs;
    @Autowired
    private ProductStore ps;
    @Autowired
    private VehicleStore vs;
    @Autowired
    private ServiceStore ss = new ServiceStore();
    @Autowired
    private OrderStore os;
    @Autowired
    private OrderService orderService = new OrderService();

    List<ProductConfiguration> articleNumberDurationList;
    Vehicle v;
    VehicleEntity safedVehicleEntity;


    @BeforeEach
    public void init(){
        Price pricePerMonth1 = new Price(15.99);
        Price pricePerMonth2 = new Price(12.99);
        Service s1 = new Service(0L, "service1");
        Service s2 = new Service(0L, "service2");
        Service s3 = new Service(0L, "service3");

        Service safedService1 = ss.safeService(s1);
        Service safedService2 = ss.safeService(s2);
        Service safedService3 = ss.safeService(s3);


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

        Product safedProduct1 = ps.saveProduct(p1);
        Product safedProduct2 = ps.saveProduct(p2);

        ProductConfiguration  pc1 = new ProductConfiguration(0L, safedProduct1, 3);
        ProductConfiguration  pc2 = new ProductConfiguration(0L, safedProduct2, 6);

        articleNumberDurationList = new ArrayList<>();
        articleNumberDurationList.add(pc1);
        articleNumberDurationList.add(pc2);

        Customer c = new Customer(0L, "payment");
        Vin vin = new Vin("vin123");
        v = new Vehicle(0L, vin, c, serviceList1);

        cs.saveCustomer(c);
        safedVehicleEntity = vs.saveVehicle(v);

    }

    @DisplayName("should place a order and update the customer")
    @Test
    public void placeOrder(){


        OrderEntity savedOrder = orderService.buy(articleNumberDurationList, v.getVin().vin());

        assertThat(savedOrder.getProducts().size()).isEqualTo(2);
        assertThat(savedOrder.getTotalPrice()).isEqualTo(125.91);
        assertThat(savedOrder.getProducts().get(0).getProductEntity().getIncludedServices().size()).isEqualTo(3);
        assertThat(savedOrder.getProducts().get(1).getProductEntity().getIncludedServices().size()).isEqualTo(2);
        assertThat(savedOrder.getProducts().get(0).getDuration()).isEqualTo(3);
        assertThat(savedOrder.getProducts().get(1).getDuration()).isEqualTo(6);
    }

    @Test
    @DisplayName("should cancel an order")
    public void cancelOrderTest(){

        Price pricePerMonth1 = new Price(15.99);
        Price pricePerMonth2 = new Price(12.99);
        Service s1 = new Service(0L, "service1");
        Service s2 = new Service(0L, "service2");
        Service s3 = new Service(0L, "service3");
        List<Service> serviceList1 = new ArrayList<>();
        List<Service> serviceList2 = new ArrayList<>();
        serviceList1.add(s1);
        serviceList1.add(s2);
        serviceList1.add(s3);
        serviceList2.add(s1);
        serviceList2.add(s3);
        ArticleNumber an1 = new ArticleNumber(123L);
        ArticleNumber an2 = new ArticleNumber(456L);
        Product p1 = new Product(0L, an1, pricePerMonth1, serviceList1);
        Product p2 = new Product(0L, an2, pricePerMonth2, serviceList2);

        ProductConfiguration  pc1 = new ProductConfiguration(0L, p1, 3);
        ProductConfiguration  pc2 = new ProductConfiguration(0L, p2, 6);

        List<ProductConfiguration> articleNumberDurationList = new ArrayList<>();
        articleNumberDurationList.add(pc1);
        articleNumberDurationList.add(pc2);

        Customer c = new Customer(0L, "payment");
        Vin vin = new Vin("vin123");
        Vehicle v = new Vehicle(0L, vin, c, serviceList1);

        vs.saveVehicle(v);

        OrderEntity savedOrder = orderService.buy(articleNumberDurationList, v.getVin().vin());

        orderService.cancelOrder(savedOrder);
        Order canceledOrder = os.getOrderById(savedOrder.getId());

        assertThat(canceledOrder.isCanceled()).isTrue();
        assertThat(canceledOrder.getCancellationDate()).isNotNull();

    }








}

