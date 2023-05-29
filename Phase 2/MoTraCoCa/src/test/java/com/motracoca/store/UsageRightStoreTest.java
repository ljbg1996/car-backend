package com.motracoca.store;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.repositorys.UsageRightRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsageRightStoreTest {

    @Mock
    private UsageRightRepository usageRightRepository;

    @InjectMocks
    private UsageRightStore usageRightStore;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

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
        usageRightEntity.setId(1);
        usageRightEntity.setStartDate(LocalDate.now());
        usageRightEntity.setEndDate(LocalDate.now().plusDays(7));
        usageRightEntity.setCoveredService(new ServiceEntity());
        usageRightEntity.setCoveredVehicle(new VehicleEntity());
        usageRightEntity.setCoveredCustomer(new CustomerEntity());
        usageRightEntity.setFromProduct(new ProductEntity());
        usageRightEntity.setFromOrder(new OrderEntity());


        when(usageRightRepository.save(usageRightEntity)).thenReturn(usageRightEntity);


        UsageRight savedUsageRight = usageRightStore.saveUsageRight(usageRight);


        verify(usageRightRepository, times(1)).save(usageRightEntity);


        assertEquals(usageRight.getId(), savedUsageRight.getId());
        assertEquals(usageRight.getStartDate(), savedUsageRight.getStartDate());
        assertEquals(usageRight.getEndDate(), savedUsageRight.getEndDate());
        assertEquals(usageRight.getCoveredService().getClass(), savedUsageRight.getCoveredService().getClass());
        assertEquals(usageRight.getCoveredVehicle().getVin(), savedUsageRight.getCoveredVehicle().getVin());
        assertEquals(usageRight.getCoveredCustomer().getId(), savedUsageRight.getCoveredCustomer().getId());
        assertEquals(usageRight.getFromProduct().getId(), savedUsageRight.getFromProduct().getId());
        assertEquals(usageRight.getFromOrder().getId(), savedUsageRight.getFromOrder().getId());
    }


    @Test
    public void testFindUsageRightById() {

        UsageRightEntity usageRightEntity = new UsageRightEntity();
        usageRightEntity.setId(1L);
        usageRightEntity.setStartDate(LocalDate.of(2023, 5, 29));
        usageRightEntity.setEndDate(LocalDate.of(2023, 6, 29));


        when(usageRightRepository.findById(1L)).thenReturn(Optional.of(usageRightEntity));


        UsageRight foundUsageRight = usageRightStore.findUsageRightById(1L);


        verify(usageRightRepository, times(1)).findById(1L);


        assertEquals(usageRightEntity.getId(), foundUsageRight.getId());
        assertEquals(usageRightEntity.getStartDate(), foundUsageRight.getStartDate());
        assertEquals(usageRightEntity.getEndDate(), foundUsageRight.getEndDate());
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


        when(usageRightRepository.findAll()).thenReturn(Arrays.asList(usageRightEntity1, usageRightEntity2));


        List<UsageRight> foundUsageRights = usageRightStore.findUsageRightsByVin("VIN1");


        verify(usageRightRepository, times(1)).findAll();


        assertEquals(1, foundUsageRights.size());


        UsageRight foundUsageRight = foundUsageRights.get(0);
        assertEquals(usageRightEntity1.getId(), foundUsageRight.getId());
        assertEquals(usageRightEntity1.getStartDate(), foundUsageRight.getStartDate());
        assertEquals(usageRightEntity1.getEndDate(), foundUsageRight.getEndDate());
    }

}
