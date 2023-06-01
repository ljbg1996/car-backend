package com.motracoca.repositorys;


import com.motracoca.entities.CustomerEntity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerEntityRepositoryTest {

    @Autowired
    private CustomerRepository cr;

    @AfterEach
    public void cleanup() {
        cr.deleteAll();
    }

    @DisplayName("should store a customer with vehicleList")
    @Test
    public void storeCustomerTest() {

        CustomerEntity c1 = new CustomerEntity();
        c1.setPaymentInfo("Paypal");
        CustomerEntity c2 = new CustomerEntity();
        c2.setPaymentInfo("Transfer");

        cr.save(c1);
        cr.save(c2);

        final List<CustomerEntity> customerEntityList = cr.findAll();

        assertThat(customerEntityList.size()).isNotNull();
        assertThat(customerEntityList.size()).isEqualTo(2);

        assertThat(customerEntityList.get(0).getPaymentInfo()).isEqualTo("Paypal");
        assertThat(customerEntityList.get(1).getPaymentInfo()).isEqualTo("Transfer");
    }


}
