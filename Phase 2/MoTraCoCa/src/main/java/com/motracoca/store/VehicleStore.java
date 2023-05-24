package com.motracoca.store;

import com.motracoca.entities.VehicleEntity;

import com.motracoca.model.Service;
import com.motracoca.model.UsageRight;
import com.motracoca.model.Vehicle;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.VehicleEntityRepository;
import com.motracoca.repositorys.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleStore {
    private final VehicleRepository vehicleRepository;

    public Vehicle convertToVehicle(VehicleEntity vehicleEntity) {
        List<Service> serviceList = vehicleEntity.getServiceEntityList().stream()
                .map(ServiceStore::convertToService)
                .collect(Collectors.toList());

        List<UsageRight> usageRightList = vehicleEntity.getUsageRightEntityList().stream()
                .map(UsageRightStore::convertToUsageRight)
                .collect(Collectors.toList());

        return new Vehicle(vehicleEntity.getId(), new Vin(vehicleEntity.getVin()), serviceList, usageRightList);
    }



    private final Map<String, VehicleEntity> vehicleMap = new HashMap<>();




    public void addVehicle(VehicleEntity vehicle) {
        vehicleMap.put(vehicle.getVin(), vehicle);
        log.info("Added vehicle with VIN {} and ID {}", vehicle.getVin(), vehicle.getId());
    }

    public Optional<Vehicle> getVehicleByVin(String vin) {
        Vehicle vehicle = vehicleMap.get(vin);
        if (vehicle != null) {
            log.info("Retrieved vehicle with VIN {} and ID{}", vehicle.getVin(), vehicle.getId());
            return Optional.of(vehicle);
        } else {
            log.warn("No vehicle found with VIN {}", vin);
            return Optional.empty();
        }
    }


}
