package com.motracoca.repositorys;

import com.motracoca.entities.ProductEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.ArticleNumber;
import com.motracoca.model.Service;
import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductEntityRepositoryTest {
    private final long ID = 12341234;
    private final double PRICE = 420.0;
    private final ServiceEntity SERVICE1 = new ServiceEntity();
    private final ServiceEntity SERVICE2 = new ServiceEntity();
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void init() {
        ProductEntity productEntity = new ProductEntity();

        SERVICE1.setName("SERVICE1");
        SERVICE2.setName("SERVICE2");

        SERVICE1.setId(123123L);
        SERVICE2.setId(123124L);


        productEntity.setId(ID);
        productEntity.setPrice(PRICE);
        final long ARTICLENUMBER = 8150815;
        productEntity.setArticleNumber(ARTICLENUMBER);
        productEntity.setIncludedServices(List.of(SERVICE1, SERVICE2));
    }

}
