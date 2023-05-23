package com.motracoca.store;

import com.motracoca.entities.VehicleEntity;

import com.motracoca.repositorys.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleStore {

    private final VehicleRepository vehicleRepository;

    public VehicleEntity saveVehicle(VehicleEntity vehicleEntity) {
        log.info("saving vehicle: {}", vehicleEntity.getVin());
        return vehicleRepository.save(vehicleEntity);
    }

    public Optional<VehicleEntity> getVehicleById(Long id) {
        log.info("Getting vehicle by VIN: {}", id);
        return vehicleRepository.findById(id);
    }


}
