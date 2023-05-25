package com.motracoca.store;

import com.motracoca.entities.UsageRightEntity;
import com.motracoca.model.UsageRight;

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

public class UsageRightStore {

    public static UsageRight convertToUsageRight(UsageRightEntity usageRightEntity) {
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
}
