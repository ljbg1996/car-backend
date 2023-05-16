package com.motracoca.model;

import lombok.RequiredArgsConstructor;
import java.time.LocalDate;



@RequiredArgsConstructor
public class UsageRight {

    private final long id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Product product;




}
