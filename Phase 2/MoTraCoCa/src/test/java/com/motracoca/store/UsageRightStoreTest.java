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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


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
        Vehicle vehicle = new Vehicle(12345L, new Vin("Vehicle1"),new Customer(1234L, "paid"),new ArrayList<>());
        Customer customer = new Customer(12345L, "paid");
        Product product = new Product(1234L,new ArticleNumber(123456L), new Price(99.99), new ArrayList<>());
        Order order = new Order(12345, true, LocalDate.now(), new Vehicle(123456L, new Vin("Vehicle2"),
                new Customer(1235L, "paid"),new ArrayList<>()), new Customer(12346L, "paid"),
                new Price(88.88), LocalDate.now(), new ArrayList<>(), true);
        UsageRight usageRight = new UsageRight(1, LocalDate.now(), LocalDate.now().plusDays(7),
                service, vehicle, customer, product, order);

        UsageRightEntity usageRightEntity = new UsageRightEntity();
        usageRightEntity.setId(1L);

        when(usageRightRepository.save(any(UsageRightEntity.class))).thenReturn(usageRightEntity);

        UsageRight savedUsageRight = usageRightStore.saveUsageRight(usageRight);

        assertThat(savedUsageRight.getId()).isEqualTo(1L);
        verify(usageRightRepository, times(1)).save(any(UsageRightEntity.class));

    }


    @Test
    public void testFindUsageRightById() {

        UsageRightEntity usageRightEntity1 = new UsageRightEntity();
        usageRightEntity1.setId(1L);
        usageRightEntity1.setStartDate(LocalDate.of(2023, 5, 29));
        usageRightEntity1.setEndDate(LocalDate.of(2023, 6, 29));


        UsageRightEntity usageRightEntity2 = new UsageRightEntity();
        usageRightEntity2.setId(2L);
        usageRightEntity2.setStartDate(LocalDate.of(2023, 6, 1));
        usageRightEntity2.setEndDate(LocalDate.of(2023, 7, 1));


        when(usageRightRepository.findById(1L)).thenReturn(Optional.of(usageRightEntity1));


        UsageRight foundUsageRight = usageRightStore.findUsageRightById(1L);


        verify(usageRightRepository, times(1)).findById(1L);

        assertThat(foundUsageRight.getId()).isEqualTo(usageRightEntity1.getId());
        assertThat(foundUsageRight.getStartDate()).isEqualTo(usageRightEntity1.getStartDate());
        assertThat(foundUsageRight.getEndDate()).isEqualTo(usageRightEntity1.getEndDate());
    }

    @Test
    public void testFindUsageRightsByVin() {

        UsageRightEntity usageRightEntity1 = new UsageRightEntity();
        usageRightEntity1.setId(1L);
        usageRightEntity1.setStartDate(LocalDate.of(2023, 5, 29));
        usageRightEntity1.setEndDate(LocalDate.of(2023, 6, 29));
        usageRightEntity1.setCoveredVehicle(new VehicleEntity());

        UsageRightEntity usageRightEntity2 = new UsageRightEntity();
        usageRightEntity2.setId(2L);
        usageRightEntity2.setStartDate(LocalDate.of(2023, 6, 1));
        usageRightEntity2.setEndDate(LocalDate.of(2023, 7, 1));
        usageRightEntity2.setCoveredVehicle(new VehicleEntity());



        List<UsageRightEntity> foundUsageRights = usageRightStore.findByCoveredVehicle(new VehicleEntity());
        
        verify(usageRightRepository, times(1));

        Assertions.assertThat(foundUsageRights.size()).isEqualTo(1);
        UsageRightEntity foundUsageRight = foundUsageRights.get(0);
        Assertions.assertThat(foundUsageRight.getId()).isEqualTo(usageRightEntity1.getId());
        Assertions.assertThat(foundUsageRight.getStartDate()).isEqualTo(usageRightEntity1.getStartDate());
        Assertions.assertThat(foundUsageRight.getEndDate()).isEqualTo(usageRightEntity1.getEndDate());
    }

}
