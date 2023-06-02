package com.motracoca.services;

import com.motracoca.model.*;
import com.motracoca.repositorys.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FinaliseOrderServiceTest {
    private OrderService orderService;
    private Order order;
    private Vehicle vehicle;
    private Customer customer;
    private Service service;
    private ProductConfiguration productConfiguration;
    private Product product;

    @BeforeEach
    void init() {
        service = new Service(
                999L,
                "Service1"
        );
        customer = new Customer(
                5555L,
                "PayDude"
        );
        product = new Product(
                3245324L,
                new ArticleNumber(23425L),
                new Price(3214L),
                List.of(service)
        );
        vehicle = new Vehicle(
                3333L,
                new Vin("test234"),
                customer,
                List.of(service)
        );
        productConfiguration = new ProductConfiguration(
                39495L,
                product,
                6
        );
        order = new Order(
                1223L,
                false,
                LocalDate.of(1, 2, 2023),
                vehicle,
                customer,
                new Price(42.0),
                LocalDate.of(1, 2, 2023),
                List.of(productConfiguration),
                false,
                null
        );
    }

//    @Test
//    public void markOrderAsCompleted() {
////        // given
////        when(orderService.ge(userEntity.getId())).thenReturn(Optional.of(userEntity));
////        // when
////        final User user = userStore.findUser("U12345789");
////        // then
////        assertThat(user.getFirstName()).isEqualTo("Max");
//    }


}
