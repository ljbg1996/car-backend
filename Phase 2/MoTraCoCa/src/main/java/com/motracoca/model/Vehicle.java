package com.motracoca.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@Getter
public class Vehicle {

    private final long id;
    private final Vin vin;
    private final Customer owner;
    private final List<Service> serviceList;



}


