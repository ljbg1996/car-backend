package com.motracoca.repositorys;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.VehicleEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VehicleEntityRepositoryTest {

    @Autowired
    private VehicleRepository vr;


    @DisplayName("should store a customer with vehicleList")
    @Test
    public void storeCustomerVehicle() {

        ServiceEntity se1 = new ServiceEntity();
        ServiceEntity se2 = new ServiceEntity();

        List<ServiceEntity> serviceList = new ArrayList<>();
        serviceList.add(se1);
        serviceList.add(se2);

        CustomerEntity c1 = new CustomerEntity();
        c1.setPaymentInfo("Paypal");

        VehicleEntity v1 = new VehicleEntity();
        v1.setVin("Vin123");
        v1.setOwner(c1);
        v1.setServiceEntityList(serviceList);

        vr.save(v1);

        List<VehicleEntity> savedVehicle = vr.findAll();

        assertThat(savedVehicle).isNotNull();







    }
}
