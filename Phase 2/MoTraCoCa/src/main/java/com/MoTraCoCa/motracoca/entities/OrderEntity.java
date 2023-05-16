package com.motracoca.motracoca.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;

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
    //private double totalPrice;

    //@Temporal(TemporalType.TIMESTAMP)
    private final LocalDate date;

    @OneToMany
    private final List<ProductConfigurationEntity> products;

}


