package com.motracoca.store;

import com.motracoca.entities.VehicleEntity;
import com.motracoca.repositorys.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;


class VehicleStoreTest {

    @Mock
    private VehicleRepository vehicleRepository;

    private VehicleStore vehicleStore;

    @BeforeEach
    void setup() {
        vehicleStore = new VehicleStore(vehicleRepository);
    }

    @Test
    void testSaveVehicle(){

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setVin("AB12");
        vehicle.setId(Long.parseLong("A1899HU387N2k89BF124DF34AS"));
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        vehicleStore.saveVehicle(vehicle);

        verify(vehicleRepository, times(1)).save(vehicle);



    }





}

