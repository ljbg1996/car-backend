package com.motracoca.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.*;
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


@ExtendWith(MockitoExtension.class)
class VehicleStoreTest {

    //@MockBean
    @Mock
    private VehicleRepository vehicleRepository;


    VehicleStore vs;

    VehicleEntity vehicleEntity;

    List<ServiceEntity> serviceList;

    @BeforeEach
    void init() {
        vehicleEntity = new VehicleEntity();
        vehicleEntity.setVin("ABC123456");
        vehicleEntity.setId(1234);
        serviceList = new ArrayList<>();
        ServiceEntity service1 = new ServiceEntity();
        serviceList.add(service1);
        vehicleEntity.setServiceEntityList(serviceList);
        vehicleEntity.setOwner(new CustomerEntity());

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void findVehicleByVin() {

        vs = new VehicleStore(vehicleRepository);
/*
        Service s1 = new Service(0L, "service1");
        Service s2 = new Service(0L, "service2");
        Service s3 = new Service(0L, "service3");

        safedService1 = ss.safeService(s1);
        safedService2 = ss.safeService(s2);
        safedService3 = ss.safeService(s3);


        List<Service> serviceList1 = new ArrayList<>();
        List<Service> serviceList2 = new ArrayList<>();
        serviceList1.add(safedService1);
        serviceList1.add(safedService2);
        serviceList1.add(safedService3);
        serviceList2.add(safedService1);
        serviceList2.add(safedService3);

        Customer c = new Customer(0L, "payment");

        Customer safedCustomer = cs.saveCustomer(c);
        safedCustomerEntitity = CustomerStore.convertToCustomerEntity(safedCustomer);

        Vin vin = new Vin("vin123");
        v = new Vehicle(0L, vin, safedCustomer, serviceList1);

        safedVehicleEntity = vs.saveVehicle(v);*/

        when(vehicleRepository.findById(vehicleEntity.getId())).thenReturn(Optional.of(vehicleEntity));
        Vehicle vehicle = vs.getVehicle(vehicleEntity.getId());

    }

    @Test
    public void testGetVehicleByVin() {
        String vin = "ABC123";
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(1L);
        vehicleEntity.setVin(vin);
        vehicleEntity.setServiceEntityList(serviceList);
        vehicleEntity.setOwner(new CustomerEntity());

        vs = new VehicleStore(vehicleRepository);
/*
        Service s1 = new Service(0L, "service1");
        Service s2 = new Service(0L, "service2");
        Service s3 = new Service(0L, "service3");

        safedService1 = ss.safeService(s1);
        safedService2 = ss.safeService(s2);
        safedService3 = ss.safeService(s3);


        List<Service> serviceList1 = new ArrayList<>();
        List<Service> serviceList2 = new ArrayList<>();
        serviceList1.add(safedService1);
        serviceList1.add(safedService2);
        serviceList1.add(safedService3);
        serviceList2.add(safedService1);
        serviceList2.add(safedService3);

        Customer c = new Customer(0L, "payment");

        Customer safedCustomer = cs.saveCustomer(c);
        safedCustomerEntitity = CustomerStore.convertToCustomerEntity(safedCustomer);

        Vin vin = new Vin("vin123");
        v = new Vehicle(0L, vin, safedCustomer, serviceList1);

        safedVehicleEntity = vs.saveVehicle(v);
*/


        when(vehicleRepository.findByVin(vehicleEntity.getVin())).thenReturn(vehicleEntity);

        Vehicle actualVehicle = vs.getVehicleByVin(vehicleEntity.getVin());


        assertThat(actualVehicle.getVin().vin()).isEqualTo(vin);
    }

    @Test
    public void testGetServicesByVin() {
        vs = new VehicleStore(vehicleRepository);

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

        when(vehicleRepository.findByVin(vin)).thenReturn(vehicleEntity);

        List<Service> expectedServices = new ArrayList<>();
        expectedServices.add(ServiceStore.convertToService(serviceEntity1));
        expectedServices.add(ServiceStore.convertToService(serviceEntity2));

        List<Service> actualServices = vs.getServicesByVin(vin);


        assertThat(actualServices).isEqualTo(expectedServices);
    }

}




