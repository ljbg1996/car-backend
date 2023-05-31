package com.motracoca.store;

import com.motracoca.entities.ServiceEntity;

import com.motracoca.entities.VehicleEntity;

import com.motracoca.model.Service;

import com.motracoca.model.Vehicle;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.motracoca.store.CustomerStore.convertToCustomer;
import static com.motracoca.store.CustomerStore.convertToCustomerEntity;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleStore {

    @Autowired
    private VehicleRepository vr;

    public static com.motracoca.model.Vehicle convertToVehicle(VehicleEntity vehicleEntity) {
        List<Service> serviceList = vehicleEntity.getServiceEntityList().stream()
                .map(ServiceStore::convertToService)
                .collect(Collectors.toList());


        return new com.motracoca.model.Vehicle(vehicleEntity.getId(), new Vin(vehicleEntity.getVin()), convertToCustomer(vehicleEntity.getOwner()), serviceList);
    }

    public static VehicleEntity convertToVehicleEntity(com.motracoca.model.Vehicle vehicle) {
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

    public Vehicle getVehicle(long id) {

        Optional<VehicleEntity> optionalVehicleEntity = vr.findById(id);
        if (optionalVehicleEntity.isPresent()) {
            VehicleEntity vehicleEntity = optionalVehicleEntity.get();
            com.motracoca.model.Vehicle vehicle = convertToVehicle(vehicleEntity);
            return vehicle;
        } else {
            throw new IllegalArgumentException("vehicle not found");
        }
    }


    public void saveVehicle(Vehicle v) {
        VehicleEntity ve = convertToVehicleEntity(v);
        vr.save(ve);
    }


    public Vehicle getVehicleByVin(String vin) {
        VehicleEntity ve = vr.getVehicleEntityByVin(vin);
        com.motracoca.model.Vehicle v = convertToVehicle(ve);
        return v;
    }
}
