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
public class VehicleEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne
    private String vin;

    @OneToMany
    private List<ServiceEntity> serviceEntityList;

    private List<UsageRightEntity> usageRightEntityList;


}


