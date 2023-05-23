package com.motracoca.store;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.repositorys.CustomerRepository;
import com.motracoca.store.CustomerStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomerStoreTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerStore customerStore;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveCustomer_ShouldReturnSavedCustomer() {
        // Arrange
        CustomerEntity customer = new CustomerEntity();
        customer.setId(1L);
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        CustomerEntity savedCustomer = customerStore.saveCustomer(customer);

        // Assert
        assertThat(savedCustomer).isEqualTo(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void getAllCustomers_ShouldReturnAllCustomers() {
        // Arrange
        List<CustomerEntity> customers = new ArrayList<>();
        CustomerEntity customer1 = new CustomerEntity();
        customer1.setId(1L);
        CustomerEntity customer2 = new CustomerEntity();
        customer2.setId(2L);
        customers.add(customer1);
        customers.add(customer2);
        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<CustomerEntity> result = customerStore.getAllCustomers();

        // Assert
        assertThat(result).isEqualTo(customers);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void getCustomerById_ExistingCustomerId_ShouldReturnCustomer() {
        // Arrange
        long customerId = 1L;
        CustomerEntity customer = new CustomerEntity();
        customer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Act
        Optional<CustomerEntity> result = customerStore.getCustomerById(customerId);

        // Assert
        assertThat(result).isPresent().contains(customer);
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void getCustomerById_NonExistingCustomerId_ShouldReturnEmptyOptional() {
        // Arrange
        long nonExistingCustomerId = 100L;
        when(customerRepository.findById(nonExistingCustomerId)).thenReturn(Optional.empty());

        // Act
        Optional<CustomerEntity> result = customerStore.getCustomerById(nonExistingCustomerId);

        // Assert
        assertThat(result).isEmpty();
        verify(customerRepository, times(1)).findById(nonExistingCustomerId);
    }

    @Test
    void deleteCustomer_ShouldDeleteCustomer() {
        // Arrange
        CustomerEntity customer = new CustomerEntity();
        customer.setId(1L);

        // Act
        customerStore.deleteCustomer(customer);

        // Assert
        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    void updateCustomer_ShouldUpdateCustomer() {
        // Arrange
        CustomerEntity customer = new CustomerEntity();
        customer.setId(1L);

        // Act
        customerStore.updateCustomer(customer);

        // Assert
        verify(customerRepository, times(1)).save(customer);
    }


}
