/*package com.motracoca.controller;

import com.motracoca.entities.OrderEntity;
import com.motracoca.model.Order;
import com.motracoca.model.ProductConfiguration;
import com.motracoca.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderRESTController {

    private OrderService orderService;

    @Autowired
    public OrderRESTController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/buy")
    public ResponseEntity<OrderEntity>buy(@RequestBody PurchaseRequest purchaseRequest) {
        OrderEntity saveOrder = orderService.buy(purchaseRequest);
        return ResponseEntity.ok(saveOrder);

    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderEntity> getOrder(@PathVariable String orderId) {

        long orderId1 = Long.parseLong(orderId);

        try {
            OrderEntity order = orderService.getOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }





}*/
