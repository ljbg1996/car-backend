package com.motracoca.motracoca.repositorys;

import com.motracoca.motracoca.entities.CustomerEntity;
import com.motracoca.motracoca.entities.Vehicle;
import com.motracoca.motracoca.entities.Vin;
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


        Vin vin1 = new Vin("123");
        Vehicle v1 = new Vehicle(1, vin1);

        CustomerEntity c1 = new CustomerEntity(1);
        c1.getVehicleList().add(v1);

        Vin vin2 = new Vin("456");
        Vehicle v2 = new Vehicle(2, vin2);

        CustomerEntity c2 = new CustomerEntity(2);
        c2.getVehicleList().add(v2);

        cr.save(c1);
        cr.save(c2);


        final List<CustomerEntity> customerEntityList = cr.findAll();

        assertThat(customerEntityList.size()).isNotNull();
        assertThat(customerEntityList.size()).isEqualTo(2);

        assertThat(customerEntityList.get(0).getVehicleList().size()).isEqualTo(1);
        assertThat(customerEntityList.get(1).getVehicleList().size()).isEqualTo(1);
    }


}
