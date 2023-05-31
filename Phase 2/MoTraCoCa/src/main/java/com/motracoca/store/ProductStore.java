package com.motracoca.store;

import com.motracoca.entities.ProductEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.ArticleNumber;
import com.motracoca.model.Price;
import com.motracoca.model.Product;
import com.motracoca.model.Service;
import com.motracoca.repositorys.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ProductStore {
    @Autowired
    private final ProductRepository productRepository;


    //TODO Use Optionals
    public Product findProductByArticleNumber(ArticleNumber articleNumber) {
        ProductEntity productEntity = productRepository
                .findProductEntityByArticleNumber(articleNumber.articleNumber());

        if (productEntity != null) {
            return convertToProduct(productEntity);
        } else throw new IllegalArgumentException(
                "No Product found for ArticleNumber " + articleNumber.articleNumber());
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

    public Product saveProduct(Product product) {
        productRepository.flush();
        ProductEntity productEntity = convertToProductEntity(product);
        ProductEntity safedProductEntity = productRepository.save(productEntity);
        Product safedProduct = convertToProduct(safedProductEntity);
        return safedProduct;
    }

    public List<Service> findAllServices() {
        List<ServiceEntity> serviceEntityList  = productRepository.findAllServices();
        List<Service> serviceList = new ArrayList<>();

        if (!serviceEntityList.isEmpty()) {
            serviceList = serviceEntityList.stream()
                    .map(serviceEntity ->ServiceStore.convertToService(serviceEntity))
                    .collect(Collectors.toList());
        } else throw new IllegalArgumentException(
                "No Services found");

        return serviceList;

    }
}
