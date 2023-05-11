package com.motracoca.motracoca.repositorys;

import com.motracoca.motracoca.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
