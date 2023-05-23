package com.motracoca.store;

import com.motracoca.entities.ProductConfigurationEntity;
import com.motracoca.model.ProductConfiguration;

import static com.motracoca.store.ProductStore.convertToProduct;

public class ProductConfigurationStore {
    public static ProductConfiguration convertToProductConfiguration(ProductConfigurationEntity productConfigurationEntity) {
        return new ProductConfiguration(
                convertToProduct(productConfigurationEntity.getProduct()),
                productConfigurationEntity.getDuration()
        );
    }
}
