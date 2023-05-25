package com.motracoca.store;

import com.motracoca.entities.UsageRightEntity;
import com.motracoca.model.*;
import com.motracoca.repositorys.UsageRightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
public class UsageRightStoreTest {

    @Mock
    private UsageRightRepository usageRightRepository;

    @InjectMocks
    private UsageRightStore usageRightStore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   /* @Test
    void saveUsageRight() {
        long id = 1L;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        Service coveredService = new Service();
        Vehicle coveredVehicle = new Vehicle();
        Customer coveredCustomer = new Customer();
        Product fromProduct = new Product();
        Order fromOrder = new Order();
        UsageRight usageRight = new UsageRight(id, startDate, endDate, coveredService, coveredVehicle, coveredCustomer, fromProduct, fromOrder);
    }*/


}
