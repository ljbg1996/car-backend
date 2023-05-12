package com.motracoca.motracoca.repositorys;

import com.motracoca.motracoca.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
