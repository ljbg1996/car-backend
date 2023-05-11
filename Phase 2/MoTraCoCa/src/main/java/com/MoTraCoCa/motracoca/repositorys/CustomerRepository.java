package com.motracoca.motracoca.repositorys;

import com.motracoca.motracoca.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
