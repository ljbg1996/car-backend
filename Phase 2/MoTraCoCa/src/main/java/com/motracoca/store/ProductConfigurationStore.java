package com.motracoca.store;

import com.motracoca.entities.ProductConfigurationEntity;
import com.motracoca.model.ProductConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.motracoca.store.ProductStore.convertToProduct;
import static com.motracoca.store.ProductStore.convertToProductEntity;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductConfigurationStore {

    public static ProductConfiguration convertToProductConfiguration(ProductConfigurationEntity productConfigurationEntity) {
        return new ProductConfiguration(productConfigurationEntity.getId(),
                convertToProduct(productConfigurationEntity.getProductEntity()),
                productConfigurationEntity.getDuration()
        );
    }
    public static ProductConfigurationEntity convertToProductConfigurationEntity(ProductConfiguration productConfiguration) {
        ProductConfigurationEntity productConfigurationEntity = new ProductConfigurationEntity();
        productConfigurationEntity.setId(productConfiguration.id());
        productConfigurationEntity.setProductEntity(convertToProductEntity(productConfiguration.product()));
        productConfigurationEntity.setDuration(productConfiguration.duration());
        return productConfigurationEntity;
    }


}
