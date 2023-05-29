package com.motracoca.store;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.Service;
import com.motracoca.model.Vehicle;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.VehicleRepository;
import com.motracoca.store.ServiceStore;
import com.motracoca.store.VehicleStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VehicleStoreTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleStore vehicleStore;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetVehicleByVin() {
        String vin = "ABC123";
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(1L);
        vehicleEntity.setVin(vin);

        when(vehicleRepository.findByVin(vin)).thenReturn(Optional.of(vehicleEntity));

        Vehicle expectedVehicle = new Vehicle(1L, new Vin(vin), null, null);
        Vehicle actualVehicle = vehicleStore.getVehicleByVin(vin);

        assertEquals(expectedVehicle, actualVehicle);
    }

    @Test
    public void testGetServicesByVin() {
        String vin = "ABC123";
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(1L);
        vehicleEntity.setVin(vin);

        ServiceEntity serviceEntity1 = new ServiceEntity();
        serviceEntity1.setId(1L);
        serviceEntity1.setName("Service 1");

        ServiceEntity serviceEntity2 = new ServiceEntity();
        serviceEntity2.setId(2L);
        serviceEntity2.setName("Service 2");

        List<ServiceEntity> serviceEntities = new ArrayList<>();
        serviceEntities.add(serviceEntity1);
        serviceEntities.add(serviceEntity2);

        vehicleEntity.setServiceEntityList(serviceEntities);

        when(vehicleRepository.findByVin(vin)).thenReturn(Optional.of(vehicleEntity));

        List<Service> expectedServices = new ArrayList<>();
        expectedServices.add(ServiceStore.convertToService(serviceEntity1));
        expectedServices.add(ServiceStore.convertToService(serviceEntity2));

        List<Service> actualServices = vehicleStore.getServicesByVin(vin);

        assertEquals(expectedServices, actualServices);
    }
}




