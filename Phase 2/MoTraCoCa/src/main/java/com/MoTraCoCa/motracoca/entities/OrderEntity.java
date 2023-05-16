package com.motracoca.motracoca.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    private boolean isPayed;

    @ManyToOne
    private VehicleEntity vehicleEntity;

    //@Embedded
    private double totalPrice;

    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate date;

    @OneToMany
    private List<ProductConfigurationEntity> products;

}


