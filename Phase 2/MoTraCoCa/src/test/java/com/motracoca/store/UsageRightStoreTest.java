package com.motracoca.store;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.repositorys.UsageRightRepository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;

@SpringBootTest
public class UsageRightStoreTest {

    @InjectMocks
    private UsageRightStore usageRightStore;

    @Mock
    private UsageRightRepository usageRightRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
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
        usageRightEntity.getCoveredVehicle().setServiceEntityList(new ArrayList<>());
        usageRightEntity.setCoveredCustomer(new CustomerEntity());


        ServiceEntity serviceEntity = new ServiceEntity();
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setServiceEntityList(new ArrayList<>());
        CustomerEntity customerEntity = new CustomerEntity();

        usageRightEntity.setCoveredService(serviceEntity);
        usageRightEntity.setCoveredVehicle(vehicleEntity);
        usageRightEntity.setCoveredCustomer(customerEntity);

        when(usageRightRepository.save(any(UsageRightEntity.class))).thenReturn(usageRightEntity);

        UsageRight savedUsageRight = usageRightStore.saveUsageRight(usageRight);

        verify(usageRightRepository, times(1)).save(any(UsageRightEntity.class));

        Assertions.assertThat(savedUsageRight.getId()).isEqualTo(usageRight.getId());
        Assertions.assertThat(savedUsageRight.getStartDate()).isEqualTo(usageRight.getStartDate());
        Assertions.assertThat(savedUsageRight.getEndDate()).isEqualTo(usageRight.getEndDate());
        Assertions.assertThat(savedUsageRight.getCoveredService().getClass()).isEqualTo(usageRight.getCoveredService().getClass());
        Assertions.assertThat(savedUsageRight.getCoveredVehicle().getVin()).isEqualTo(usageRight.getCoveredVehicle().getVin());
        Assertions.assertThat(savedUsageRight.getCoveredCustomer().getId()).isEqualTo(usageRight.getCoveredCustomer().getId());
        Assertions.assertThat(savedUsageRight.getFromProduct().getId()).isEqualTo(usageRight.getFromProduct().getId());
        Assertions.assertThat(savedUsageRight.getFromOrder().getId()).isEqualTo(usageRight.getFromOrder().getId());

        /*when(usageRightRepository.save(any(UsageRightEntity.class))).thenReturn(usageRightEntity);

        UsageRight savedUsageRight = usageRightStore.saveUsageRight(usageRight);

        verify(usageRightRepository, times(1)).save(any(UsageRightEntity.class));

        Assertions.assertThat(savedUsageRight.getId()).isEqualTo(usageRight.getId());
        Assertions.assertThat(savedUsageRight.getStartDate()).isEqualTo(usageRight.getStartDate());
        Assertions.assertThat(savedUsageRight.getEndDate()).isEqualTo(usageRight.getEndDate());
        Assertions.assertThat(savedUsageRight.getCoveredService().getClass()).isEqualTo(usageRight.getCoveredService().getClass());
        Assertions.assertThat(savedUsageRight.getCoveredVehicle().getVin()).isEqualTo(usageRight.getCoveredVehicle().getVin());
        Assertions.assertThat(savedUsageRight.getCoveredCustomer().getId()).isEqualTo(usageRight.getCoveredCustomer().getId());
        Assertions.assertThat(savedUsageRight.getFromProduct().getId()).isEqualTo(usageRight.getFromProduct().getId());
        Assertions.assertThat(savedUsageRight.getFromOrder().getId()).isEqualTo(usageRight.getFromOrder().getId());
        */

    }


    @Test
    public void testFindUsageRightById() {

        UsageRightEntity usageRightEntity = new UsageRightEntity();
        usageRightEntity.setId(1L);
        usageRightEntity.setStartDate(LocalDate.of(2023, 5, 29));
        usageRightEntity.setEndDate(LocalDate.of(2023, 6, 29));

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L); // Set the ID or other required properties for the customer
        usageRightEntity.setCustomerEntity(customerEntity);

        usageRightEntity.setCoveredService(new ServiceEntity());
        usageRightEntity.setCoveredVehicle(new VehicleEntity());

        VehicleEntity vehicleEntity = new VehicleEntity();
        usageRightEntity.getCoveredVehicle().setServiceEntityList(new ArrayList<>());

        when(usageRightRepository.findById(1L)).thenReturn(Optional.of(usageRightEntity));

        UsageRight foundUsageRight = usageRightStore.findUsageRightById(1L);

        verify(usageRightRepository, times(1)).findById(1L);

        Assertions.assertThat(foundUsageRight.getId()).isEqualTo(usageRightEntity.getId());
        Assertions.assertThat(foundUsageRight.getStartDate()).isEqualTo(usageRightEntity.getStartDate());
        Assertions.assertThat(foundUsageRight.getEndDate()).isEqualTo(usageRightEntity.getEndDate());
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


        when(usageRightRepository.findAll()).thenReturn(Arrays.asList(usageRightEntity1));
        final var listOngoingStubbing = when(usageRightRepository.findByCoveredVehicle(Mockito.any(VehicleEntity.class)))
                .thenReturn(Arrays.asList(usageRightEntity1));
        List<UsageRightEntity> foundUsageRights = usageRightStore.findByCoveredVehicle(new VehicleEntity());
        
        verify(usageRightRepository, times(1));

        Assertions.assertThat(foundUsageRights.size()).isEqualTo(1);
        UsageRightEntity foundUsageRight = foundUsageRights.get(0);
        Assertions.assertThat(foundUsageRight.getId()).isEqualTo(usageRightEntity1.getId());
        Assertions.assertThat(foundUsageRight.getStartDate()).isEqualTo(usageRightEntity1.getStartDate());
        Assertions.assertThat(foundUsageRight.getEndDate()).isEqualTo(usageRightEntity1.getEndDate());
    }

}
