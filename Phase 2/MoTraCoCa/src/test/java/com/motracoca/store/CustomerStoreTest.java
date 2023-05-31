package com.motracoca.store;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.model.Customer;
import com.motracoca.repositorys.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
//@SpringBootTest
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

    @Test
    public void testSaveCustomer() {
        // Arrange
        Customer customer = new Customer(1L, "paymentInfo");
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setPaymentInfo("paymentInfo");

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        // Act
        Customer savedCustomer = customerStore.saveCustomer(customer);

        // Assert
        assertThat(savedCustomer.getId()).isEqualTo(1L);
        assertThat(savedCustomer.getPaymentInfo()).isEqualTo("paymentInfo");
        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
    }


    @Test
    public void testUpdateCustomer() {
        // Arrange
        long customerId = 1L;
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setPaymentInfo("oldPaymentInfo");
        Customer customer = new Customer(customerId, "newPaymentInfo");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);

        // Act
        Customer updatedCustomer = customerStore.updateCustomer(customer);

        // Assert
        assertThat(updatedCustomer.getId()).isEqualTo(customerId);
        assertThat(updatedCustomer.getPaymentInfo()).isEqualTo("newPaymentInfo");
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(customerEntity);
    }

    @Test
    public void testDeleteCustomerById() {
        // Arrange
        long customerId = 1L;
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));

        // Act
        customerStore.deleteCustomerById(customerId);

        // Assert
        verify(customerRepository, times(1)).delete(customerEntity);
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setId(1L);
        customerEntity1.setPaymentInfo("John Doe");

        CustomerEntity customerEntity2 = new CustomerEntity();
        customerEntity2.setId(2L);
        customerEntity2.setPaymentInfo("Jane Smith");

        List<CustomerEntity> customerEntities = new ArrayList<>();
        customerEntities.add(customerEntity1);
        customerEntities.add(customerEntity2);

        when(customerRepository.findAll()).thenReturn(customerEntities);

        // Act
        List<Customer> customers = customerStore.getAllCustomers();

        // Assert
        assertThat(customers.size()).isEqualTo(2);

        // Verify conversion for each customer entity
        for (int i = 0; i < customerEntities.size(); i++) {
            CustomerEntity entity = customerEntities.get(i);
            Customer customer = customers.get(i);

            assertThat(customer.getId()).isEqualTo(entity.getId());
            assertThat(customer.getPaymentInfo()).isEqualTo(entity.getPaymentInfo());
            // Add assertions for other properties if needed
        }

        verify(customerRepository, times(1)).findAll();
    }



    @Test
    public void testConvertToCustomer() {
        // Arrange
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setPaymentInfo("paymentInfo");

        // Act
        Customer customer = CustomerStore.convertToCustomer(customerEntity);

        // Assert
        assertThat(customer.getId()).isEqualTo(1L);
        assertThat(customer.getPaymentInfo()).isEqualTo("paymentInfo");
    }

    @Test
    public void testConvertToCustomerEntity() {
        // Arrange
        Customer customer = new Customer(1L, "paymentInfo");

        // Act
        CustomerEntity customerEntity = CustomerStore.convertToCustomerEntity(customer);

        // Assert
        assertThat(customerEntity.getId()).isEqualTo(1L);
        assertThat(customerEntity.getPaymentInfo()).isEqualTo("paymentInfo");
    }

}
