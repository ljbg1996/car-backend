package com.motracoca.motracoca.model;


import jakarta.persistence.*;
import lombok.Value;

import java.time.LocalDate;



@Value
@Entity
public class UsageRight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate endDate;

    @OneToOne
    private Product product;

    public UsageRight() {
        this.id = 0L;
        this.startDate = null;
        this.endDate = null;
        this.product = null;
    }

    public UsageRight(LocalDate startDate, LocalDate endDate, Product product) {
        this.id = 0L;
        this.startDate = startDate;
        this.endDate = endDate;
        this.product = product;
    }
}
