package com.motracoca.motracoca.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.*;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class VehicleServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private final ServiceEntity implementedService;

    @OneToMany(mappedBy = "vehicleService")
    private List<UsageRightEntity> usageRightEntities; // entspricht aktuell nicht den Anforderungen

    @ManyToOne
    private VehicleEntity vehicleEntity;  // ? is not in class diagramm
    // constructor, getters and setters


}
