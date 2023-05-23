package com.motracoca.store;

import com.motracoca.entities.VehicleEntity;
import com.motracoca.repositorys.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleStore {

    VehicleRepository vehicleRepository;

    public VehicleStore(VehicleRepository vehicleRepository){

    }

    public void saveVehicle(VehicleEntity vehicle) {
        vehicleRepository.save(vehicle);
    }



}
