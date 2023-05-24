package com.motracoca.store;

import com.motracoca.entities.UsageRightEntity;
import com.motracoca.model.UsageRight;

import static com.motracoca.store.ProductStore.convertToProduct;
import static com.motracoca.store.ProductStore.convertToProductEntity;

public class UsageRightStore {

    public static UsageRight convertToUsageRight(UsageRightEntity usageRightEntity) {
        return new UsageRight(
                usageRightEntity.getId(),
                usageRightEntity.getStartDate(),
                usageRightEntity.getEndDate(),
                convertToProduct(usageRightEntity.getProduct())
        );
    }
    public static UsageRightEntity convertToUsageRightEntity(UsageRight usageRight) {
        UsageRightEntity usageRightEntity = new UsageRightEntity();
        usageRightEntity.setId(usageRight.getId());
        usageRightEntity.setStartDate(usageRight.getStartDate());
        usageRightEntity.setEndDate(usageRight.getEndDate());
        usageRightEntity.setProduct(convertToProductEntity(usageRight.getProduct()));

        return usageRightEntity;
    }
}
