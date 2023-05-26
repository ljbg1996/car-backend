package com.motracoca.store;

import com.motracoca.entities.ProductEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.ArticleNumber;
import com.motracoca.model.Price;
import com.motracoca.model.Product;
import com.motracoca.model.Service;
import com.motracoca.repositorys.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest
public class ProductStoreTest {

    private final long ID = 12341234;
    private final double PRICE = 420.0;
    private final ServiceEntity SERVICEENTITY1 = new ServiceEntity();
    private final ServiceEntity SERVICEENTITY2 = new ServiceEntity();
    final long ARTICLENUMBER = 8150815;
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void init() {
        SERVICEENTITY1.setName("SERVICE1");
        SERVICEENTITY2.setName("SERVICE2");

        SERVICEENTITY1.setId(11111111L);
        SERVICEENTITY2.setId(22222222L);
    }

    @Test
    public void getProductByArticleNumberTest(){
        //given

        final ProductStore productStore = new ProductStore(productRepository);

        // when
        final Product productResult = productStore
                .findProductByArticleNumber(new ArticleNumber(ARTICLENUMBER));

        // then
        Assertions.assertThat(productResult
                .getArticleNumber()
                .articleNumber())
                .isEqualTo(ARTICLENUMBER);
    }

    @Test
    public void convertToProductTest(){
//        given
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(ID);
        productEntity.setIncludedServices(List.of(SERVICEENTITY1,SERVICEENTITY2));
        productEntity.setArticleNumber(ARTICLENUMBER);
        productEntity.setPrice(PRICE);

//        when
//        then
        Assertions.assertThat(ProductStore.convertToProduct(productEntity)
                .getArticleNumber())
                .isEqualTo(new ArticleNumber(ARTICLENUMBER));
    }

    @Test
    public void convertToProductEntityTest(){
//      given
        Service service1 = new Service(11111111L,"Service1");
        Service service2 = new Service(22222222L,"Service2");

//      when
        Product product = new Product(
                ID,
                new ArticleNumber(ARTICLENUMBER),
                new Price(PRICE),
                List.of(service1, service2));
//      then
        Assertions.assertThat(ProductStore.convertToProductEntity(product).getArticleNumber()).isEqualTo(ARTICLENUMBER);
    }
    @Test
    public void saveProductTest(){
        //given
        Service service1 = new Service(11111111L,"Service1");
        Service service2 = new Service(22222222L,"Service2");
        final ProductStore productStore = new ProductStore(productRepository);
        ProductEntity productEntity = new ProductEntity();

        // when
        Product product = new Product(
                ID,
                new ArticleNumber(ARTICLENUMBER),
                new Price(PRICE),
                List.of(service1, service2));

        final Product productResult = productStore
                .findProductByArticleNumber(new ArticleNumber(ARTICLENUMBER));

        productEntity.setId(ID);
        productEntity.setPrice(PRICE);

        productEntity.setArticleNumber(ARTICLENUMBER);
        productEntity.setIncludedServices(List.of(SERVICEENTITY1, SERVICEENTITY2));

        productRepository.save(productEntity);

        // then
        Assertions.assertThat(productResult)
                .isEqualTo(product);
    }
}
