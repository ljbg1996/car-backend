package com.motracoca.store;

import com.motracoca.entities.ProductConfigurationEntity;
import com.motracoca.entities.ProductEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductConfigurationStoreTest {

    @Test
    public void testConvertToProductConfiguration() {
        // Arrange
        //given
        final ServiceEntity serviceEntity1 = new ServiceEntity();
        serviceEntity1.setId(111111L);
        serviceEntity1.setName("Service1");
        final ServiceEntity serviceEntity2 = new ServiceEntity();
        serviceEntity2.setId(222222L);
        serviceEntity2.setName("Service2");
        final long articleNumber = 8150815;
        final long id = 1L;
        final double price = 420.0;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntity.setArticleNumber(articleNumber);
        productEntity.setPrice(price);
        productEntity.setIncludedServices(List.of(serviceEntity1,serviceEntity2));

        ProductConfigurationEntity productConfigurationEntity = new ProductConfigurationEntity();
        productConfigurationEntity.setId(2L);
        productConfigurationEntity.setProductEntity(productEntity);
        productConfigurationEntity.setDuration(10);


        // Act
        ProductConfiguration productConfiguration = ProductConfigurationStore.convertToProductConfiguration(productConfigurationEntity);

        // Assert
        assertThat(productConfiguration.id()).isEqualTo(2L);
        assertThat(productConfiguration.product().getId()).isEqualTo(id);
        assertThat(productConfiguration.product().getArticleNumber().articleNumber()).isEqualTo(articleNumber);
        assertThat(productConfiguration.product().getPrice().price()).isEqualTo(price);
        assertThat(productConfiguration.product().getIncludedServices().size()).isEqualTo(2);
        assertThat(productConfiguration.product().getIncludedServices().get(1).id()).isEqualTo(222222L);
        assertThat(productConfiguration.duration()).isEqualTo(10);
    }

    @Test
    public void testConvertToProductConfigurationEntity() {
        //given
        final Service SERVICE1 = new Service(11111111L, "Service1");
        final Service SERVICE2 = new Service(22222222L, "Service2");
        final Service SERVICE3 = new Service(33333333L, "Service3");
        final long articleNumber = 8150815;
        final long ID = 12341234;
        final double PRICE = 420.0;
        //when
        final Product product = new Product(
                ID,
                new ArticleNumber(articleNumber),
                new Price(PRICE),
                List.of(SERVICE1, SERVICE2, SERVICE3));
        ProductConfiguration productConfiguration = new ProductConfiguration(1L, product, 10);

        // Act
        ProductConfigurationEntity productConfigurationEntity = ProductConfigurationStore.convertToProductConfigurationEntity(productConfiguration);

        // Assert
        assertThat(productConfigurationEntity.getId()).isEqualTo(1L);
        assertThat(productConfigurationEntity.getProductEntity().getId()).isEqualTo(ID);
        assertThat(productConfigurationEntity.getProductEntity().getArticleNumber()).isEqualTo(articleNumber);
        assertThat(productConfigurationEntity.getProductEntity().getPrice()).isEqualTo(PRICE);
        assertThat(productConfigurationEntity.getProductEntity().getIncludedServices().size()).isEqualTo(3);
        assertThat(productConfigurationEntity.getProductEntity().getIncludedServices().get(2).getId()).isEqualTo(33333333L);
        assertThat(productConfigurationEntity.getProductEntity().getIncludedServices().get(1).getName()).isEqualTo("Service2");
        assertThat(productConfigurationEntity.getDuration()).isEqualTo(10);
    }
}
