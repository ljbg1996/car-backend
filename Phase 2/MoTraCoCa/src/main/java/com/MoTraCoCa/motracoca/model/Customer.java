package com.motracoca.motracoca.model;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class Customer {


    private final long id;
    private final List<com.motracoca.motracoca.model.Vehicle> vehicleList;
    private final List<Order> orderList;
    private final String PaymentInfo;





}
