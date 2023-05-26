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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class VehicleStoreTest {

    @MockBean
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleStore vehicleStore;

    VehicleEntity vehicleEntity;

    @BeforeEach
    void init() {
        vehicleEntity = new VehicleEntity();
        vehicleEntity.setVin("ABC123456");
        vehicleEntity.setId(1234);
    }

    @Test
    void findVehicleByVin() {
        when(vehicleRepository.findById(vehicleEntity.getId())).thenReturn(Optional.of(vehicleEntity));
        final Vehicle vehicle = vehicleStore.getVehicle(vehicleEntity.getId());

    }
}




