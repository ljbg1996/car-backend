package com.motracoca.repositorys;

import com.motracoca.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {


    @Query
    public VehicleEntity findByVin(String vin);
}
