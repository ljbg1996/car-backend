package com.motracoca.repositorys;

import com.motracoca.entities.UsageRightEntity;
import com.motracoca.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageRightRepository extends JpaRepository<UsageRightEntity, Long> {
}