package com.motracoca.store;

import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.*;
import com.motracoca.repositorys.UsageRightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsageRightStoreTest {

    @Mock
    private UsageRightRepository usageRightRepository;

    @InjectMocks
    private UsageRightStore usageRightStore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void saveUsageRightTest() {
        long id = 11234L;

        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 10, 31);
        Service coveredService = new Service(12345L,"Service A");
        Customer coveredCustomer = new Customer(12345L, "paid");
        Product fromProduct = new Product(12345L, new ArticleNumber(12345L),new Price(50.00), List.of());
        Vehicle coveredVehicle = new Vehicle(12345L, new Vin("VIN123"), coveredCustomer, new ArrayList<>(){
        });
        Order fromOrder = new Order(123, true, LocalDate.of(2023, 5, 5),coveredVehicle,
               new Customer(12345L,"Sandro") , new Price(50), LocalDate.of(2023, 10, 6), new ArrayList<>(),true);

        UsageRight usageRight = new UsageRight(id, startDate, endDate, coveredService, coveredVehicle, coveredCustomer, fromProduct, fromOrder);

        Assertions.assertEquals(id, usageRight.getId());
        Assertions.assertEquals(startDate, usageRight.getStartDate());
        Assertions.assertEquals(endDate, usageRight.getEndDate());
        Assertions.assertEquals(coveredService, usageRight.getCoveredService());
        Assertions.assertEquals(coveredVehicle, usageRight.getCoveredVehicle());
        Assertions.assertEquals(coveredCustomer, usageRight.getCoveredCustomer());
        Assertions.assertEquals(fromProduct, usageRight.getFromProduct());
        Assertions.assertEquals(fromOrder, usageRight.getFromOrder());

    }


}
