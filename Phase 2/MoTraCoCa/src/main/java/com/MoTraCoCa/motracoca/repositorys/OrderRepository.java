package com.motracoca.motracoca.repositorys;

import com.motracoca.motracoca.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
