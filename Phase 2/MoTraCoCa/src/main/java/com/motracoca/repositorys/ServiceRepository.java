package com.motracoca.repositorys;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
