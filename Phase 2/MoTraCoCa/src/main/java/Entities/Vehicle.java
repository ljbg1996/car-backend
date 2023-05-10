package Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Vehicle {

    // id? (einzige Klasse ohne id)
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO) // Autogeneration nicht möglich bei vin
    @Column(name = "vin", nullable = false)
    private final Vin vin;

    @OneToMany
    private List<VehicleService> vehicleServiceList;


}


record Vin(String vin){}