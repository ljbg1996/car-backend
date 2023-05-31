package com.motracoca.controller;
import com.motracoca.entities.OrderEntity;
import com.motracoca.model.ProductConfiguration;
import com.motracoca.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class OrderRESTControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private OrderService orderService;

    private String url;
    private String vin;



    @BeforeEach
    void init() {

    }

    @Test
    public void testBuy() {
        List<ProductConfiguration> articleNumberDurationList = new ArrayList<>();
        String vin = "ABC123";

        OrderEntity saveOrder = new OrderEntity();
        when(orderService.buy(articleNumberDurationList, vin)).thenReturn(saveOrder);
    }




}
