package com.motracoca.store;

import com.motracoca.entities.VehicleEntity;
import com.motracoca.repositorys.VehicleRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;



import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class VehicleStoreTest {


    @Mock
    private VehicleRepository vehicleRepository;

    private VehicleStore vehicleStore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vehicleStore = new VehicleStore(vehicleRepository);
    }

    @Test
    void testSaveVehicle() {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setVin("123ABC");

        when(vehicleRepository.save(vehicleEntity)).thenReturn(vehicleEntity);
        VehicleEntity savedVehicle = vehicleStore.saveVehicle(vehicleEntity);

        assertEquals(vehicleEntity, savedVehicle);
        verify(vehicleRepository, times(1)).save(vehicleEntity);
    }

    @Test
    void testGetVehicleById() {
        Long vehicleId = 1L;

        VehicleEntity.setId(vehicleId);
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(VehicleEntity));
        Optional<VehicleEntity> retrievedVehicle = vehicleStore.getVehicleById(vehicleId);

        assertEquals(Optional.of(vehicleEntity), retrievedVehicle);
        verify((vehicleRepository, times(1)).findById());
    }


}

