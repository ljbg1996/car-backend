package com.motracoca.store;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.model.Customer;
import com.motracoca.repositorys.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerStoreTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerStore customerStore;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetCustomerById_Exists() {
        // Arrange
        long customerId = 1L;
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setPaymentInfo("no");
        Customer expectedCustomer = new Customer(customerId, "no");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));

        // Act
        Customer customer = customerStore.getCustomerById(customerId);

        // Assert
        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isEqualTo(expectedCustomer.getId());
        assertThat(customer.getPaymentInfo()).isEqualTo(expectedCustomer.getPaymentInfo());

        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testGetCustomerById_NotExists() {
        // ... (Arrange section same as before)
        long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerStore.getCustomerById(customerId);
        });

        // Assert
        assertThat(exception.getMessage()).isEqualTo("No user found for user id 1");
        verify(customerRepository, times(1)).findById(customerId);
    }

}
