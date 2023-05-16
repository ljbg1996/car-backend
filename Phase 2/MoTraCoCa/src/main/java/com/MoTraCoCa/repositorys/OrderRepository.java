package com.MoTraCoCa.repositorys;

import com.MoTraCoCa.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
