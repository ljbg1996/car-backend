package com.motracoca.store;

import java.util.Optional;

import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.Vehicle;
import com.motracoca.repositorys.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleStoreTest {

    @Mock
    private VehicleRepository vehicleRepository;

    private VehicleStore vehicleStore;

    VehicleEntity vehicleEntity;

    @BeforeEach
    void init() {
        vehicleEntity = new VehicleEntity();
        vehicleEntity.setVin("ABC123456");
        vehicleEntity.setId(1234);

        VehicleRepository VehicleRepository = null;
        vehicleStore = new VehicleStore(null);
    }

    @Test
    void findVehicleByVin() {
        when(vehicleRepository.findById(Long.valueOf(vehicleEntity.getVin()))).thenReturn(Optional.of(vehicleEntity));
        final Vehicle vehicle = vehicleStore.getVehicle(1234);

    }
}




