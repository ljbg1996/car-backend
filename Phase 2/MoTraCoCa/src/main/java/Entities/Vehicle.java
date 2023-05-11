package Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private final long id;

    @Column(name = "vin", nullable = false)
    private final Vin vin;

    @OneToMany
    private final List<VehicleService> vehicleServiceList = new ArrayList<>();
    //Durch List<Service> ersetzen?

    @OneToMany
    private final List<UsageRight> usageRights = new ArrayList<>();


}


record Vin(String vin){}