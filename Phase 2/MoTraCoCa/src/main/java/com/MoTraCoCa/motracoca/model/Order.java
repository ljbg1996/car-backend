package com.motracoca.motracoca.model;


import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class Order {


    private final long id;

    private final boolean isPayed;

    private final Vehicle vehicle;

    private final Price totalPrice;

    private final LocalDate date;

    private final List<ProductConfiguration> products;

}


