package com.motracoca.motracoca.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    private boolean isPayed;

    @OneToOne
    private VehicleEntity vehicleEntity;

    //@Embedded
    // private Price totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private final Date date;

    @OneToMany
    private final List<ProductConfigurationEntity> products;

}


