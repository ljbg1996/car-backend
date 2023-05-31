package com.motracoca.repositorys;

import com.motracoca.entities.ProductEntity;
import com.motracoca.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    public ProductEntity findProductEntityByArticleNumber(long articleNumber);

    /*@Query("SELECT p FROM ProductEntity WHERE p.articleNumber =:articleNumber")
    public ProductEntity getProductByArticleNumber(@Param("articleNumber") long articleNumber);*/

    @Query("SELECT DISTINCT product.includedServices FROM ProductEntity product JOIN FETCH ServiceEntity service")
    public List<ServiceEntity> findAllServices();
}
