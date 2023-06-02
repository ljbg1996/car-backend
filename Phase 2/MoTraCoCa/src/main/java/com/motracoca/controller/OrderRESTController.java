package com.motracoca.controller;

import com.motracoca.entities.OrderEntity;
import com.motracoca.model.Order;
import com.motracoca.model.ProductConfiguration;
import com.motracoca.model.Service;
import com.motracoca.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get order by articleNumber and vin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Service.class)) }),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content) })


    @PostMapping("/buy")
    public ResponseEntity<OrderEntity> buy(@RequestBody List<ProductConfiguration> articleNumberDurationList, @RequestParam("vin") String vin) {
        OrderEntity savedOrder = orderService.buy(articleNumberDurationList, vin);
        return ResponseEntity.ok(savedOrder);
    }
    
//    @GetMapping("/{orderId}")
//    public ResponseEntity<OrderEntity> getOrder(@PathVariable String orderId) {
//
//        long orderId1 = Long.parseLong(orderId);
//
//        try {
////            OrderEntity order = orderService.getOrder(orderId);
//            return ResponseEntity.ok(order);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

//    @DeleteMapping("/cancel/{orderId}")
//    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") Long orderId) {
//        OrderEntity orderEntity = orderService.getOrderEntityById(orderId);
//
//        if (orderEntity == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        boolean isCancelled = orderService.cancelOrder(orderEntity);
//
//        if (isCancelled) {
//            return ResponseEntity.ok("Order cancelled successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel order");
//        }
//    }





}
