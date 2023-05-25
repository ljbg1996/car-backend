package com.motracoca.store;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.UsageRightEntity;
import com.motracoca.entities.VehicleEntity;

import com.motracoca.model.Service;
import com.motracoca.model.UsageRight;
import com.motracoca.model.Vehicle;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.motracoca.store.CustomerStore.convertToCustomer;
import static com.motracoca.store.CustomerStore.convertToCustomerEntity;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleStore {
    private final VehicleRepository vehicleRepository;

    public static Vehicle convertToVehicle(VehicleEntity vehicleEntity) {
        List<Service> serviceList = vehicleEntity.getServiceEntityList().stream()
                .map(ServiceStore::convertToService)
                .collect(Collectors.toList());


        return new Vehicle(vehicleEntity.getId(), new Vin(vehicleEntity.getVin()), convertToCustomer(vehicleEntity.getOwner()), serviceList);
    }

    public static VehicleEntity convertToVehicleEntity(Vehicle vehicle) {
        List<ServiceEntity> serviceEntities = vehicle.getServiceList().stream()
                .map(ServiceStore::convertToServiceEntity)
                .collect(Collectors.toList());

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setId(vehicle.getId());
        vehicleEntity.setVin(vehicle.getVin().vin());
        vehicleEntity.setOwner(convertToCustomerEntity(vehicle.getOwner()));
        vehicleEntity.setServiceEntityList(serviceEntities);

        return vehicleEntity;
    }



    private final Map<String, VehicleEntity> vehicleMap = new HashMap<>();




    public void addVehicle(VehicleEntity vehicle) {
        vehicleMap.put(vehicle.getVin(), vehicle);
        log.info("Added vehicle with VIN {} and ID {}", vehicle.getVin(), vehicle.getId());
    }

    // TODO return a Vehicle Model. not optional
    public Optional<VehicleEntity> getVehicleByVin(String vin) {
        VehicleEntity vehicle = vehicleMap.get(vin);
        if (vehicle != null) {
            log.info("Retrieved vehicle with VIN {} and ID{}", vehicle.getVin(), vehicle.getId());
            return vehicle;
        } else {
            log.warn("No vehicle found with VIN {}", vin);
            return null;
        }
    }


    public void saveVehicle(Vehicle v) {
    }

    public Vehicle getVehicle(int i) {
        return null;
    }
}
