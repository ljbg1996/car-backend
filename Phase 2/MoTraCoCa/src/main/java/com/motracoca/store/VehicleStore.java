package com.motracoca.store;

import com.motracoca.entities.CustomerEntity;
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

    public Vehicle getVehicleByVin(String vin) {
        Optional<VehicleEntity> vehicleEntityOptional = vehicleRepository.findByVin(vin);
        if (vehicleEntityOptional.isPresent()) {
            VehicleEntity vehicleEntity = vehicleEntityOptional.get();
            return convertToVehicle(vehicleEntity);
        }
        return null;
    }

    public List<Service> getServicesByVin(String vin) {
        Optional<VehicleEntity> vehicleEntityOptional = vehicleRepository.findByVin(vin);
        if (vehicleEntityOptional.isPresent()) {
            VehicleEntity vehicleEntity = vehicleEntityOptional.get();
            return vehicleEntity.getServiceEntityList().stream()
                    .map(ServiceStore::convertToService)
                    .collect(Collectors.toList());
        }
        return null;
    }

}

