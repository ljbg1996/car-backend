package com.motracoca.motracoca.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class VehicleService {

    @Id
    private Long id;

    @ManyToOne
    private final Service implementedService;

    @OneToMany(mappedBy = "vehicleService")
    private List<UsageRight> usageRights; // entspricht aktuell nicht den Anforderungen

    @ManyToOne
    private Vehicle vehicle;  // ? is not in class diagramm
    // constructor, getters and setters

    public VehicleService(Long id, Service implementedService, Vehicle vehicle) {
        this.id = id;
        this.implementedService = implementedService;
        this.vehicle = vehicle;
    }
}
