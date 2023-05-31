package com.motracoca.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.Service;
import com.motracoca.model.Vehicle;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.VehicleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class VehicleStoreTest {

    //@MockBean
    @Mock
    private VehicleRepository vehicleRepository;

    //@Autowired
    @InjectMocks
    private VehicleStore vehicleStore;

    VehicleEntity vehicleEntity;

    @BeforeEach
    void init() {
        vehicleEntity = new VehicleEntity();
        vehicleEntity.setVin("ABC123456");
        vehicleEntity.setId(1234);

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void findVehicleByVin() {
        //TODO hier fehlt noch was. es wurde noch nicht in der db gespeichert
        
        when(vehicleRepository.findById(vehicleEntity.getId())).thenReturn(Optional.of(vehicleEntity));
        Vehicle vehicle = vehicleStore.getVehicle(vehicleEntity.getId());

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


        assertThat(actualVehicle).isEqualTo(expectedVehicle);
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


        assertThat(actualServices).isEqualTo(expectedServices);
    }

}




