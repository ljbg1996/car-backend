package com.motracoca.motracoca.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class Vehicle {

    private final long id;
    private final Vin vin;
    private final List<Service> serviceList;
    private final List<UsageRight> usageRightList;


}


