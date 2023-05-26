package com.motracoca.repositorys;

import com.motracoca.entities.ProductEntity;
import com.motracoca.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM Product p WHERE p.articleNumber = ?1")
    Optional<ProductEntity> getProductByArticleNumber(String articleNumber);

    @Query("SELECT DISTINCT product.includedServices FROM ProductEntity product JOIN FETCH ServiceEntity service")
    public List<ServiceEntity> findAllServices();
}
