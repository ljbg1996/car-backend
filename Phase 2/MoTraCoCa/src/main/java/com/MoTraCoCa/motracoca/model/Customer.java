package com.motracoca.motracoca.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Customer {


    private final long id;
    private final List<Vehicle> vehicleList;
    private final List<Order> orderList;
    private final String PaymentInfo;





}
