package com.motracoca.repositorys;

import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.UsageRightEntity;
import com.motracoca.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsageRightRepository extends JpaRepository<UsageRightEntity, Long> {

    @Query
    public List<UsageRightEntity> findByFromOrder(OrderEntity orderEntity);

    @Query
    public List<UsageRightEntity> findByCoveredVehicle(VehicleEntity vehicleEntity);


}