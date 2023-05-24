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
        List<Vehicle> vehicleList = customerEntity.getVehicleEntityList().stream()
                .map(VehicleStore::convertToVehicle)
                .collect(Collectors.toList());

        List<Order> orderList = customerEntity.getOrderEntityList().stream()
                .map(OrderStore::convertToOrder)
                .collect(Collectors.toList());

        return new Customer(customerEntity.getId(), vehicleList, orderList, customerEntity.getPaymentInfo());
    }

    public static CustomerEntity convertToCustomerEntity(Customer customer) {
        List<VehicleEntity> vehicleEntityList = customer.getVehicleList().stream()
                .map(VehicleStore::convertToVehicleEntity)
                .collect(Collectors.toList());

        List<OrderEntity> orderEntityList = customer.getOrderList().stream()
                .map(OrderStore::convertToOrderEntity)
                .collect(Collectors.toList());

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setVehicleEntityList(vehicleEntityList);
        customerEntity.setOrderEntityList(orderEntityList);
        customerEntity.setPaymentInfo(customer.getPaymentInfo());

        return customerEntity;
    }

    public Customer saveCustomer(CustomerEntity customerEntity) {
        CustomerEntity savedEntity = customerRepository.save(customerEntity);
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


    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Customer customer) {
        Optional<CustomerEntity> existingCustomerOptional = customerRepository.findById(customer.getId());
        if (existingCustomerOptional.isPresent()) {
            CustomerEntity existingCustomer = existingCustomerOptional.get();

            // Update other properties as needed

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















    public void deleteCustomer(CustomerEntity customer) {
        customerRepository.delete(customer);
    }

    // TODO updateCustomer sollte ein customer Model entgegen nehmen und in eine entität umgewandelt werden
    public void updateCustomer(CustomerEntity customer) {
        customerRepository.save(customer);
    }
}
