package com.motracoca.motracoca.model;


import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;



@RequiredArgsConstructor
public class UsageRight {

    private final long id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Product product;




}
