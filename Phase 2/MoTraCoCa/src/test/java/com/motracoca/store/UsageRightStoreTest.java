package com.motracoca.store;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.repositorys.UsageRightRepository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;



import java.time.LocalDate;
import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsageRightStoreTest {

    @Mock
    private UsageRightRepository usageRightRepository;


    private UsageRightStore usageRightStore;

    @BeforeEach
    void init(){usageRightStore = new UsageRightStore(usageRightRepository);}




    @Test
    public void testSaveUsageRight() {

        Service service = new Service(1234L, "Service1");
        Customer customer = new Customer(12345L, "paid");
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(service);
        Vehicle vehicle = new Vehicle(12345L, new Vin("Vehicle1"),customer,serviceList);

        Product product = new Product(1234L,new ArticleNumber(123456L), new Price(99.99), serviceList);

        ProductConfiguration productConfiguration = new ProductConfiguration(1234L, product, 6);
        List<ProductConfiguration> productConfigurationList = new ArrayList<>();
        productConfigurationList.add(productConfiguration);

        Order order = new Order(12345, true, LocalDate.now(), vehicle,
                customer, new Price(88.88), LocalDate.now(), productConfigurationList, true, LocalDate.now());
        UsageRight usageRight = new UsageRight(1L, LocalDate.now(), LocalDate.now().plusDays(7),
                service, vehicle, customer, product, order);

        UsageRightEntity usageRightEntity = usageRightStore.convertToUsageRightEntity(usageRight);

        when(usageRightRepository.save(any(UsageRightEntity.class))).thenReturn(usageRightEntity);

        UsageRight savedUsageRight = usageRightStore.saveUsageRight(usageRight);

        assertThat(savedUsageRight.getId()).isEqualTo(1L);
        verify(usageRightRepository, times(1)).save(any(UsageRightEntity.class));

    }




    @Test
    public void testFindUsageRightById() {

        Service service = new Service(1234L, "Service1");
        Customer customer = new Customer(12345L, "paid");
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(service);
        Vehicle vehicle = new Vehicle(12345L, new Vin("Vehicle1"),customer,serviceList);

        Product product = new Product(1234L,new ArticleNumber(123456L), new Price(99.99), serviceList);

        ProductConfiguration productConfiguration = new ProductConfiguration(1234L, product, 6);
        List<ProductConfiguration> productConfigurationList = new ArrayList<>();
        productConfigurationList.add(productConfiguration);

        Order order = new Order(12345, true, LocalDate.now(), vehicle,
                customer, new Price(88.88), LocalDate.now(), productConfigurationList, true, LocalDate.now());
        UsageRight usageRight = new UsageRight(1L, LocalDate.now(), LocalDate.now().plusDays(7),
                service, vehicle, customer, product, order);

        UsageRightEntity usageRightEntity = usageRightStore.convertToUsageRightEntity(usageRight);


        when(usageRightRepository.findById(1L)).thenReturn(Optional.of(usageRightEntity));


        UsageRight foundUsageRight = usageRightStore.findUsageRightById(1L);


        verify(usageRightRepository, times(1)).findById(1L);

        assertThat(foundUsageRight.getId()).isEqualTo(usageRightEntity.getId());
        assertThat(foundUsageRight.getStartDate()).isEqualTo(usageRightEntity.getStartDate());
        assertThat(foundUsageRight.getEndDate()).isEqualTo(usageRightEntity.getEndDate());
    }

    @Test
    public void testFindUsageRightsByVin() {


        VehicleEntity vehicleEntity = new VehicleEntity();



        UsageRightEntity expectedUsageRight = new UsageRightEntity();
        expectedUsageRight.setId(1L);
        expectedUsageRight.setStartDate(LocalDate.of(2023, 5, 29));
        expectedUsageRight.setEndDate(LocalDate.of(2023, 6, 29));
        expectedUsageRight.setCoveredVehicle(vehicleEntity);


        when(usageRightRepository.findByCoveredVehicle(Mockito.eq(vehicleEntity)))
                .thenReturn(Collections.singletonList(expectedUsageRight));


        List<UsageRightEntity> foundUsageRights = usageRightStore.findByCoveredVehicle(vehicleEntity);


        Assertions.assertThat(foundUsageRights.size()).isEqualTo(1);
        UsageRightEntity foundUsageRight = foundUsageRights.get(0);
        Assertions.assertThat(foundUsageRight.getId()).isEqualTo(expectedUsageRight.getId());
        Assertions.assertThat(foundUsageRight.getStartDate()).isEqualTo(expectedUsageRight.getStartDate());
        Assertions.assertThat(foundUsageRight.getEndDate()).isEqualTo(expectedUsageRight.getEndDate());


        verify(usageRightRepository, times(1)).findByCoveredVehicle(Mockito.eq(vehicleEntity));
    }

}
