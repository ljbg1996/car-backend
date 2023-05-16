package com.MoTraCoCa.model;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class Customer {


    private final long id;
    private final List<Vehicle> vehicleList;
    private final List<Order> orderList;
    private final String PaymentInfo;





}
