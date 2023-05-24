package com.motracoca.store;

import com.motracoca.entities.VehicleEntity;

import com.motracoca.model.*;
import com.motracoca.repositorys.VehicleEntityRepository;
import com.motracoca.repositorys.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleStore {
    private final VehicleRepository vehicleRepository;

    public static Vehicle convertToVehicle(VehicleEntity vehicleEntity) {
        List<Service> serviceList = vehicleEntity.getServiceEntityList().stream()
                .map(ServiceStore::convertToService)
                .collect(Collectors.toList());

        List<UsageRight> usageRightList = vehicleEntity.getUsageRightEntityList().stream()
                .map(UsageRightStore::convertToUsageRight)
                .collect(Collectors.toList());

        return new Vehicle(vehicleEntity.getId(), new Vin(vehicleEntity.getVin()), serviceList, usageRightList);
    }



    public Vehicle getVehicle(long id) {
        final Optional<VehicleEntity> vehicleEntityOptional = VehicleEntityRepository.findById(id);
        if (vehicleEntityOptional.isPresent()) {
            final VehicleEntity vehicleEntity = vehicleEntityOptional.get();
            return convertToVehicle(vehicleEntity);
        } else {
            throw new IllegalArgumentException("Vehicle not found for id " + id);
        }
    }



}
