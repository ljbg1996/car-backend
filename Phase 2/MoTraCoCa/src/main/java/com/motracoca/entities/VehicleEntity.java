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
    @Column(nullable = false)
    private long id;

    private String vin;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private List<ServiceEntity> serviceEntityList;

    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerEntity owner;

}


