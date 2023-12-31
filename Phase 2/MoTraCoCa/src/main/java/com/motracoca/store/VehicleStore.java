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
    private final VehicleRepository vr;

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


    public VehicleEntity saveVehicle(Vehicle v) {
        VehicleEntity ve = convertToVehicleEntity(v);
        VehicleEntity safedVehicle = vr.save(ve);
        return safedVehicle;
    }


    public Vehicle getVehicleByVin(String vin) {
        VehicleEntity ve = vr.findByVin(vin);
        com.motracoca.model.Vehicle v = convertToVehicle(ve);
        return v;}


    public List<Service> getServicesByVin(String vin) {
        VehicleEntity vehicleEntity = vr.findByVin(vin);

            return vehicleEntity.getServiceEntityList().stream()
                    .map(ServiceStore::convertToService)
                    .collect(Collectors.toList());

    }
}
