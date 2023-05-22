package com.motracoca.store;

import com.motracoca.entities.CustomerEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CustomerStoreTest {

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private CustomerStore customerStore;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        customerStore = new CustomerStore(entityManagerFactory);
    }

    @Test
    @DisplayName("Test Create Customer")
    void testCreateCustomer() {
        // Create a new customer
        CustomerEntity customer = new CustomerEntity();
        // Set customer properties
        // ...

        // Mock the transaction begin and commit
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        // Invoke the createCustomer method
        customerStore.createCustomer(customer);

        // Verify that persist was called on the entityManager
        verify(entityManager).persist(customer);
    }

    @Test
    @DisplayName("Test Get Customer by ID")
    void testGetCustomerById() {
        // Create a mock customer
        CustomerEntity customer = mock(CustomerEntity.class);

        // Mock the find method to return the mock customer
        when(entityManager.find(CustomerEntity.class, 1L)).thenReturn(customer);

        // Invoke the getCustomerById method
        CustomerEntity retrievedCustomer = customerStore.getCustomerById(1L);

        // Assertions
        assertThat(retrievedCustomer).isNotNull();
        assertThat(retrievedCustomer).isEqualTo(customer);

        // Verify that find was called on the entityManager
        verify(entityManager).find(CustomerEntity.class, 1L);
    }

    @Test
    @DisplayName("Test Update Customer")
    void testUpdateCustomer() {
        // Create a new customer
        CustomerEntity customer = new CustomerEntity();
        // Set customer properties
        // ...

        // Mock the transaction begin and commit
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        // Invoke the updateCustomer method
        customerStore.updateCustomer(customer);

        // Verify that merge was called on the entityManager
        verify(entityManager).merge(customer);
    }

    @Test
    @DisplayName("Test Delete Customer")
    void testDeleteCustomer() {
        // Create a new customer
        CustomerEntity customer = new CustomerEntity();
        // Set customer properties
        // ...

        // Mock the transaction begin and commit
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        // Mock the find method to return the customer
        when(entityManager.find(CustomerEntity.class, customer.getId())).thenReturn(customer);

        // Invoke the deleteCustomer method
        customerStore.deleteCustomer(customer);

        // Verify that find and remove were called on the entityManager
        verify(entityManager).find(CustomerEntity.class, customer.getId());
        verify(entityManager).remove(customer);
    }

    // Add additional test cases as needed
}
