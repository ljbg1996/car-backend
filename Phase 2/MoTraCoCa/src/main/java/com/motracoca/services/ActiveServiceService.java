package com.motracoca.services;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.UsageRightEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.CustomerRepository;
import com.motracoca.store.ServiceStore;
import com.motracoca.store.UsageRightStore;
import com.motracoca.store.VehicleStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActiveServiceService {

    @Autowired
    UsageRightStore urs;
    @Autowired
    VehicleStore vs;



    public List<com.motracoca.model.Service> getActiveServices(Vin vin) {

        VehicleEntity vehicleEntityFromDb = VehicleStore.convertToVehicleEntity(vs.getVehicleByVin(vin.vin()));
        List<UsageRightEntity> usageRightEntityList = urs.findByCoveredVehicle(vehicleEntityFromDb);

        List<com.motracoca.model.Service> servicesWithVin = new ArrayList<>();

        for (UsageRightEntity ure : usageRightEntityList) {
            ServiceEntity coveredServiceEntity = ure.getCoveredService();
            com.motracoca.model.Service coveredService = ServiceStore.convertToService(coveredServiceEntity);
            servicesWithVin.add(coveredService);

        }

        return servicesWithVin;
    }


}
