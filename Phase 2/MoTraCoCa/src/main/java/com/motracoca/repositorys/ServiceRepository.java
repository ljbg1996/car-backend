package com.motracoca.repositorys;

import com.motracoca.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
