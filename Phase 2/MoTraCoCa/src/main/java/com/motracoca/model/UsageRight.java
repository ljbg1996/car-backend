package com.motracoca.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;



@RequiredArgsConstructor
@Getter
public class UsageRight {

    private final long id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Service coveredService;
    private final Vehicle coveredVehicle;
    private final Customer coveredCustomer;
    private final Product product;




}
