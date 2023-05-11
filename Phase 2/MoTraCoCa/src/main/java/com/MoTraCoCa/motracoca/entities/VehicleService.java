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
    private Service implementedService;
    @OneToMany(mappedBy = "vehicleService")
    private List<UsageRight> usageRights; // entspricht aktuell nicht den Anforderungen
    @ManyToOne
    private Vehicle vehicle;  // ? is not in class diagramm
    // constructor, getters and setters

}
