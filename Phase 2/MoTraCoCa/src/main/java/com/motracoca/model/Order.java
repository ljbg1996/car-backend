package com.motracoca.model;


import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class Order {


    private long id;

    private final boolean isPayed;

    private final Vehicle vehicle;

    private final double totalPrice;

    private final LocalDate date;

    private final List<ProductConfiguration> products;

}


