package com.motracoca.entities;


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

    @ManyToOne(cascade = CascadeType.ALL)
    private VehicleEntity vehicleEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    private CustomerEntity customerEntity;

    //@Embedded
    private double totalPrice;

    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductConfigurationEntity> products;



}


