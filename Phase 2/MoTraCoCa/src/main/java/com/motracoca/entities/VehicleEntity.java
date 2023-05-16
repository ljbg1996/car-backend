package com.motracoca.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class VehicleEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    private String vin;

    @OneToMany
    private List<ServiceEntity> serviceEntityList;

    @OneToMany
    private List<UsageRightEntity> usageRightEntityList;


}


