package com.motracoca.store;

import com.motracoca.entities.ProductEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.ArticleNumber;
import com.motracoca.model.Price;
import com.motracoca.model.Product;
import com.motracoca.model.Service;

import java.util.List;
import java.util.stream.Collectors;

public class ProductStore {
    public Product getProductByArticleNumber(String articleNumber) {
        return null;
    }

    public static Product convertToProduct(ProductEntity productEntity) {
        List<Service> includedServices = productEntity.getIncludedServices().stream()
                .map(ServiceStore::convertToService)
                .collect(Collectors.toList());

        return new Product(
                productEntity.getId(),
                new ArticleNumber(productEntity.getArticleNumber()),
                new Price(productEntity.getPrice()),
                includedServices
        );
    }
    public static ProductEntity convertToProductEntity(Product product) {
        List<ServiceEntity> includedServiceEntities = product.getIncludedServices().stream()
                .map(ServiceStore::convertToServiceEntity)
                .collect(Collectors.toList());
        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(product.getId());
        productEntity.setArticleNumber(product.getArticleNumber().articleNumber());
        productEntity.setPrice(product.getPrice().price());
        productEntity.setIncludedServices(includedServiceEntities);

        return productEntity;
    }
}
