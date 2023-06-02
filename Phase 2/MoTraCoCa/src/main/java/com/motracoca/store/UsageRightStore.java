package com.motracoca.store;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.UsageRightEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.Service;
import com.motracoca.model.UsageRight;
import com.motracoca.repositorys.UsageRightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.List;

import static com.motracoca.store.CustomerStore.convertToCustomer;
import static com.motracoca.store.CustomerStore.convertToCustomerEntity;
import static com.motracoca.store.OrderStore.convertToOrder;
import static com.motracoca.store.OrderStore.convertToOrderEntity;
import static com.motracoca.store.ProductStore.convertToProduct;
import static com.motracoca.store.ProductStore.convertToProductEntity;
import static com.motracoca.store.ServiceStore.convertToService;
import static com.motracoca.store.ServiceStore.convertToServiceEntity;
import static com.motracoca.store.VehicleStore.convertToVehicle;
import static com.motracoca.store.VehicleStore.convertToVehicleEntity;
@Component
@RequiredArgsConstructor
@Slf4j
public class UsageRightStore {


    @Autowired
    private final UsageRightRepository usageRightRepository;

    public UsageRight convertToUsageRight(UsageRightEntity usageRightEntity) {
        return new UsageRight(
                usageRightEntity.getId(),
                usageRightEntity.getStartDate(),
                usageRightEntity.getEndDate(),
                convertToService(usageRightEntity.getCoveredService()),
                convertToVehicle(usageRightEntity.getCoveredVehicle()),
                convertToCustomer(usageRightEntity.getCoveredCustomer()),
                convertToProduct(usageRightEntity.getFromProduct()),
                convertToOrder(usageRightEntity.getFromOrder())

        );
    }
    public static UsageRightEntity convertToUsageRightEntity(UsageRight usageRight) {
        UsageRightEntity usageRightEntity = new UsageRightEntity();
        usageRightEntity.setId(usageRight.getId());
        usageRightEntity.setStartDate(usageRight.getStartDate());
        usageRightEntity.setEndDate(usageRight.getEndDate());
        usageRightEntity.setCoveredService(convertToServiceEntity(usageRight.getCoveredService()));
        usageRightEntity.setCoveredVehicle(convertToVehicleEntity(usageRight.getCoveredVehicle()));
        usageRightEntity.setCoveredCustomer(convertToCustomerEntity(usageRight.getCoveredCustomer()));
        usageRightEntity.setFromProduct(convertToProductEntity(usageRight.getFromProduct()));
        usageRightEntity.setFromOrder(convertToOrderEntity(usageRight.getFromOrder()));

        return usageRightEntity;
    }

    public List<UsageRightEntity> findByCoveredVehicle(VehicleEntity vehicle){

        return usageRightRepository.findByCoveredVehicle(vehicle);

    }


    public UsageRight saveUsageRight(UsageRight usageRight) {
        UsageRightEntity entity = convertToUsageRightEntity(usageRight);
        UsageRightEntity savedEntity = usageRightRepository.save(entity);
        return convertToUsageRight(savedEntity);
    }

    public UsageRight findUsageRightById(long id) {
        Optional<UsageRightEntity> usageRightEntityOptional = usageRightRepository.findById(id);
        return usageRightEntityOptional.map(this::convertToUsageRight).orElse(null);
    }


}
