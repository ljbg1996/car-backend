package com.motracoca.repositorys;

import com.motracoca.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
}
