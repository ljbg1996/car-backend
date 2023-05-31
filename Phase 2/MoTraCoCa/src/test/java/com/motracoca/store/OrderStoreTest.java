package com.motracoca.store;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderStoreTest {

    @Autowired
    OrderStore os = new OrderStore();

    Order order;
    OrderEntity orderEntity;

    @BeforeEach
    public void init() {


        Service service= new Service(0L, "Service1");

        List<Service> serviceList = new ArrayList<>();
        serviceList.add(service);
        Customer customer = new Customer(0L, "paypal");

        Vin vin = new Vin("Vin123");
        Vehicle vehicle = new Vehicle(0L, vin, customer, serviceList);
        Price price = new Price(10.99);
        ArticleNumber articleNumber = new ArticleNumber(12345L);
        Product product = new Product(0L, articleNumber, price, serviceList);

        List<ProductConfiguration> productConfigurationList = new ArrayList<>();
        ProductConfiguration productConfiguration = new ProductConfiguration(0L, product, 3);

        productConfigurationList.add(productConfiguration);
        Price totalPrice = new Price(32.97);
        order = new Order(0L, false, null, vehicle, customer, totalPrice, LocalDate.now(), productConfigurationList, false);
        orderEntity = OrderStore.convertToOrderEntity(order);

    }

    @Test
    public void convertModelToEntityTest(){

        OrderEntity orderEntityFromTest = OrderStore.convertToOrderEntity(order);

        assertThat(orderEntityFromTest.getTotalPrice()).isEqualTo(order.getTotalPrice().price());

    }




    @Test
    public void convertEntityToModelTest(){

        Order orderFromTest = OrderStore.convertToOrder(orderEntity);

        assertThat(orderFromTest.getTotalPrice().price()).isEqualTo(orderEntity.getTotalPrice());
    }









}
