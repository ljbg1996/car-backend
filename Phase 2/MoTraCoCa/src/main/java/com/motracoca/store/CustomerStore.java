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


    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public static Customer convertToCustomer(CustomerEntity customerEntity){
        List<Vehicle> vehicleList = customerEntity.getVehicleEntityList().stream()
                .map(VehicleStore::convertToVehicle)
                .collect(Collectors.toList());

        List<Order> orderList = customerEntity.getOrderEntityList().stream()
                .map(OrderStore::convertToOrder)
                .collect(Collectors.toList());

        return new Customer(customerEntity.getId(), vehicleList, orderList, customerEntity.getPaymentInfo());
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

    public void updateCustomer(CustomerEntity customer) {
        customerRepository.save(customer);
    }
}
