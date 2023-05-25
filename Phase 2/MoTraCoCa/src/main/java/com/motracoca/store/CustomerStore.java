package com.motracoca.store;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.repositorys.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerStore {

    @Autowired
    private final CustomerRepository customerRepository;

    public static Customer convertToCustomer(CustomerEntity customerEntity){

        return new Customer(customerEntity.getId(), customerEntity.getPaymentInfo());
    }

    public static CustomerEntity convertToCustomerEntity(Customer customer) {

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(customer.getId());
        customerEntity.setPaymentInfo(customer.getPaymentInfo());

        return customerEntity;
    }


    public Customer saveCustomer(Customer customer) {
        CustomerEntity savedEntity = customerRepository.save(convertToCustomerEntity(customer));
        return CustomerStore.convertToCustomer(savedEntity);
    }

    public void deleteCustomerById(long id) {
        Optional<CustomerEntity> existingCustomerOptional = customerRepository.findById(id);
        if (existingCustomerOptional.isPresent()) {
            customerRepository.delete(existingCustomerOptional.get());
        } else {
            throw new IllegalArgumentException("No customer found for ID: " + id);
        }
    }


    public List<Customer> getAllCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        List<Customer> customers = customerEntities.stream().map(CustomerStore::convertToCustomer).collect(Collectors.toList());
        return customers;
    }

    public Customer updateCustomer(Customer customer) {
        Optional<CustomerEntity> existingCustomerOptional = customerRepository.findById(customer.getId());
        if (existingCustomerOptional.isPresent()) {
            CustomerEntity existingCustomer = existingCustomerOptional.get();

            // Update other properties as needed
            existingCustomer.setPaymentInfo(customer.getPaymentInfo());

            CustomerEntity updatedEntity = customerRepository.save(existingCustomer);
            return convertToCustomer(updatedEntity);
        } else {
            throw new IllegalArgumentException("No customer found for ID: " + customer.getId());
        }
    }


    public Customer getCustomerById(long id) {
        final Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if (customerEntityOptional.isPresent()) {
            final CustomerEntity customerEntity = customerEntityOptional.get();
            return convertToCustomer(customerEntity);
        } else {
            throw new IllegalArgumentException("No user found for user id " + id);
        }
    }
}
