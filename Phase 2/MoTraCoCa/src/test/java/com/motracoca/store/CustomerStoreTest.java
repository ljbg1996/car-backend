package com.motracoca.store;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.model.Customer;
import com.motracoca.repositorys.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        Customer expectedCustomer = new Customer(customerId, null, null, null);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));

        // Act
        Customer customer = customerStore.getCustomerById(customerId);

        // Assert
        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isEqualTo(expectedCustomer.getId());
        assertThat(customer.getPaymentInfo()).isEqualTo(expectedCustomer.getPaymentInfo());
        assertThat(customer.getOrderList()).isEqualTo(expectedCustomer.getOrderList());
        assertThat(customer.getVehicleList()).isEqualTo(expectedCustomer.getVehicleList());

        verify(customerRepository, times(1)).findById(customerId);
    }


}
