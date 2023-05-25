package com.motracoca.store;

import com.motracoca.entities.ProductConfigurationEntity;
import com.motracoca.entities.ProductEntity;
import com.motracoca.model.ProductConfiguration;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductConfigurationStoreTest {

    @Test
    public void testConvertToProductConfiguration() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setArticleNumber(3L);

        ProductConfigurationEntity productConfigurationEntity = new ProductConfigurationEntity();
        productConfigurationEntity.setId(1L);
        productConfigurationEntity.setProductEntity(productEntity);
        productConfigurationEntity.setDuration(10);

        // Act
        ProductConfiguration productConfiguration = ProductConfigurationStore.convertToProductConfiguration(productConfigurationEntity);

        // Assert
        assertThat(productConfiguration.id()).isEqualTo(1L);
        assertThat(productConfiguration.product().getId()).isEqualTo(1L);
        assertThat(productConfiguration.product().getArticleNumber()).isEqualTo(3L);
        assertThat(productConfiguration.duration()).isEqualTo(10);
    }

    @Test
    public void testConvertToProductConfigurationEntity() {
        // Arrange
        Product product = new Product(1L, "Product");
        ProductConfiguration productConfiguration = new ProductConfiguration(1L, product, 10);

        // Act
        ProductConfigurationEntity productConfigurationEntity = ProductConfigurationStore.convertToProductConfigurationEntity(productConfiguration);

        // Assert
        assertThat(productConfigurationEntity.getId(), is(1L));
        assertThat(productConfigurationEntity.getProductEntity().getId(), is(1L));
        assertThat(productConfigurationEntity.getProductEntity().getName(), is("Product"));
        assertThat(productConfigurationEntity.getDuration(), is(10));
    }
}
