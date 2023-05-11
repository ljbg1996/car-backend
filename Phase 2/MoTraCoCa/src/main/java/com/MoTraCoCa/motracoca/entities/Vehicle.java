package com.motracoca.motracoca.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne
    private String vin;

    @OneToMany
    private List<VehicleService> vehicleServiceList;


}


