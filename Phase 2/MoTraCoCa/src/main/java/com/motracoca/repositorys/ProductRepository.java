package com.motracoca.repositorys;

import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.ProductEntity;
import com.motracoca.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

        @Query("SELECT p FROM Product p WHERE p.articleNumber = ?1")
        Optional<ProductEntity> getProductByArticleNumber(String articleNumber);

}
