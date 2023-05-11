package com.motracoca.motracoca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private final long id;

    //paymentInfo
    @OneToMany( cascade= CascadeType.ALL )
    private List<Vehicle> vehicleList = new ArrayList<>();
    @OneToMany( cascade= CascadeType.ALL )
    private List<Order> orderList;


    @Column(name = "Payment information")
    String PaymentInfo;



}
