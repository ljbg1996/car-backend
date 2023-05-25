package com.motracoca.store;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.UsageRightEntity;
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

    public static Vehicle convertToVehicle(VehicleEntity vehicleEntity) {
        List<Service> serviceList = vehicleEntity.getServiceEntityList().stream()
                .map(ServiceStore::convertToService)
                .collect(Collectors.toList());

        List<UsageRight> usageRightList = vehicleEntity.getUsageRightEntityList().stream()
                .map(UsageRightStore::convertToUsageRight)
                .collect(Collectors.toList());

        return new Vehicle(vehicleEntity.getId(), new Vin(vehicleEntity.getVin()), serviceList, usageRightList);
    }

    public static VehicleEntity convertToVehicleEntity(Vehicle vehicle) {
        List<ServiceEntity> serviceEntities = vehicle.getServiceList().stream()
                .map(ServiceStore::convertToServiceEntity)
                .collect(Collectors.toList());

        List<UsageRightEntity> usageRightEntities = vehicle.getServiceList().stream()
                .map(UsageRightStore::convertToUsageRightEntity)
                .collect(Collectors.toList());

        return new VehicleEntity(vehicle.getId(), vehicle.getVin(), serviceEntities, usageRightEntities);
    }



    private final Map<String, VehicleEntity> vehicleMap = new HashMap<>();




    public void addVehicle(VehicleEntity vehicle) {
        vehicleMap.put(vehicle.getVin(), vehicle);
        log.info("Added vehicle with VIN {} and ID {}", vehicle.getVin(), vehicle.getId());
    }

    // TODO return a Vehicle Model. not optional
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


    public void saveVehicle(Vehicle v) {
    }
}
