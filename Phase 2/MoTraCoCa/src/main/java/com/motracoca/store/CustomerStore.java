package com.motracoca.store;

import com.motracoca.entities.CustomerEntity;
import com.motracoca.model.Customer;
import com.motracoca.repositorys.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

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

    public Customer getCustomerById(long id) {
        final Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if (customerEntityOptional.isPresent()) {
            final CustomerEntity customerEntity = customerEntityOptional.get();

            List<Vehicle> vehicleList = customerEntity.getVehicleEntityList().stream()
                    .map(this::convertToVehicle)
                    .collect(Collectors.toList());

            List<Order> orderList = customerEntity.getOrderEntityList().stream()
                    .map(this::convertToOrder)
                    .collect(Collectors.toList());

            return new Customer(customerEntity.getId(), vehicleList, orderList, customerEntity.getPaymentInfo());
        } else throw new IllegalArgumentException("No user found for user id " + id);
    }


    public void deleteCustomer(CustomerEntity customer) {
        customerRepository.delete(customer);
    }

    public void updateCustomer(CustomerEntity customer) {
        customerRepository.save(customer);
    }
}
