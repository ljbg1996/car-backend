package Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Vehicle {

    private final Vin vin;
    private List<VehicleService> vehicleServiceList;


}


record Vin(String vin){}