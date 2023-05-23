package com.motracoca.store;

import com.motracoca.entities.ProductEntity;
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
}
