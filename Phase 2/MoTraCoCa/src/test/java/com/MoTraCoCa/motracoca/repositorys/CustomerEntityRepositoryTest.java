package com.motracoca.motracoca.repositorys;

import com.motracoca.motracoca.entities.CustomerEntity;
import com.motracoca.motracoca.entities.VehicleEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerEntityRepositoryTest {


    //private EntityManagerFactory emf = Persistence.createEntityManagerFactory("motracoca");

    @Autowired
    private CustomerRepository cr;



    @Test
    void storeCustomerTest() {


        VehicleEntity v1 = new VehicleEntity();

        CustomerEntity c1 = new CustomerEntity();
        c1.getVehicleEntityList().add(v1);

        VehicleEntity v2 = new VehicleEntity();

        CustomerEntity c2 = new CustomerEntity();
        c2.getVehicleEntityList().add(v2);

        cr.save(c1);
        cr.save(c2);


        final List<CustomerEntity> customerEntityList = cr.findAll();

        assertThat(customerEntityList.size()).isNotNull();
        assertThat(customerEntityList.size()).isEqualTo(2);

        assertThat(customerEntityList.get(0).getVehicleEntityList().size()).isEqualTo(1);
        assertThat(customerEntityList.get(1).getVehicleEntityList().size()).isEqualTo(1);
    }


}
