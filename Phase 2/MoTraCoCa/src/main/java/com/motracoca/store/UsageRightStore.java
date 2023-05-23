package com.motracoca.store;

import com.motracoca.entities.UsageRightEntity;
import com.motracoca.model.UsageRight;

import static com.motracoca.store.ProductStore.convertToProduct;

public class UsageRightStore {

    public static UsageRight convertToUsageRight(UsageRightEntity usageRightEntity) {
        return new UsageRight(
                usageRightEntity.getId(),
                usageRightEntity.getStartDate(),
                usageRightEntity.getEndDate(),
                convertToProduct(usageRightEntity.getProduct())
        );
    }
}
