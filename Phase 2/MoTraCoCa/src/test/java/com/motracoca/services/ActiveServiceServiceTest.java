package com.motracoca.services;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.repositorys.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActiveServiceServiceTest {

    CustomerRepository customerRepository;

    @Test
    public void getActiveServicesTest(){
        ActiveServiceService activeServiceService = new ActiveServiceService();


        VehicleEntity v1 = new VehicleEntity();

        CustomerEntity c1 = new CustomerEntity();

        ServiceEntity s1 = new ServiceEntity();
        ServiceEntity s2 = new ServiceEntity();

        v1.setServiceEntityList(List.of(s1,s2));

        c1.setVehicleEntityList(List.of(v1));

        customerRepository.save(c1);
    }
}
