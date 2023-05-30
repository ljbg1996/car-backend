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
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ProductStoreTest {


    private final ServiceEntity SERVICEENTITY1 = new ServiceEntity();
    private final ServiceEntity SERVICEENTITY2 = new ServiceEntity();
    private final ServiceEntity SERVICEENTITY3 = new ServiceEntity();



    @BeforeEach
    public void init() {
        SERVICEENTITY1.setName("SERVICE1");
        SERVICEENTITY2.setName("SERVICE2");
        SERVICEENTITY3.setName("SERVICE3");

        SERVICEENTITY1.setId(11111111L);
        SERVICEENTITY2.setId(22222222L);
        SERVICEENTITY3.setId(3333333L);
    }

    @Test
    public void getProductByArticleNumberTest() {
        //given
        final long ARTICLENUMBER = 8150815;
        final long ID = 12341234;
        final double PRICE = 420.0;
        final ProductStore productStore = new ProductStore();

        final Service SERVICE1 = new Service(11111111L, "Service1");
        final Service SERVICE2 = new Service(22222222L, "Service2");

        final Product product = new Product(
                ID,
                new ArticleNumber(ARTICLENUMBER),
                new Price(PRICE),
                List.of(SERVICE1, SERVICE2));


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
    public void convertToProductTest() {
//      given
        final long ARTICLENUMBER = 8150815;
        final long ID = 12341234;
        final double PRICE = 420.0;
        ProductEntity productEntity = new ProductEntity();
//      when
        productEntity.setId(ID);
        productEntity.setIncludedServices(List.of(SERVICEENTITY1, SERVICEENTITY2));
        productEntity.setArticleNumber(ARTICLENUMBER);
        productEntity.setPrice(PRICE);


//      then
        Assertions.assertThat(ProductStore.convertToProduct(productEntity)
                        .getArticleNumber())
                .isEqualTo(new ArticleNumber(ARTICLENUMBER));
    }

    @Test
    public void convertToProductEntityTest() {
//      given
        final Service SERVICE1 = new Service(11111111L, "Service1");
        final Service SERVICE2 = new Service(22222222L, "Service2");
        final long ARTICLENUMBER = 8150815;
        final long ID = 12341234;
        final double PRICE = 420.0;
//      when
        final Product product = new Product(
                ID,
                new ArticleNumber(ARTICLENUMBER),
                new Price(PRICE),
                List.of(SERVICE1, SERVICE2));
//      then
        Assertions.assertThat(ProductStore.convertToProductEntity(product).getArticleNumber()).isEqualTo(ARTICLENUMBER);
    }

    @Test
    public void saveProductTest() {
        //given
        final Service SERVICE1 = new Service(11111111L, "Service1");
        final Service SERVICE2 = new Service(22222222L, "Service2");

        final long ARTICLENUMBER = 8150815;
        final long ID = 12341234;
        final double PRICE = 420.0;

        final ProductStore productStore = new ProductStore();
//        ProductEntity productEntity = new ProductEntity();

        // when
        final Product product1 = new Product(
                ID,
                new ArticleNumber(ARTICLENUMBER),
                new Price(PRICE),
                List.of(SERVICE1, SERVICE2));

//        productEntity.setId(ID);
//        productEntity.setPrice(PRICE);
//        productEntity.setArticleNumber(ARTICLENUMBER);
//        productEntity.setIncludedServices(List.of(SERVICEENTITY1, SERVICEENTITY2));

        productStore.saveProduct(product1);


        final Product productResult = productStore
                .findProductByArticleNumber(new ArticleNumber(ARTICLENUMBER));


        // then
        System.out.println(product1.getId()+" "+productResult.getId());
        Assertions.assertThat(productResult.getArticleNumber())
                .isEqualTo(product1.getArticleNumber());
    }

    @Test
    public void getServicesTest() {
//        given
        final Service SERVICE1 = new Service(11111111L, "Service1");
        final Service SERVICE2 = new Service(22222222L, "Service2");
        final Service SERVICE3 = new Service(33333333L, "Service3");

        final long ARTICLENUMBER1 = 8150815;
        final long ARTICLENUMBER2 = 8150816;
        final long ID1 = 12341234;
        final long ID2 = 12341235;
        final double PRICE = 420.0;

        final ProductStore productStore = new ProductStore();

        final Product product1 = new Product(
                ID1,
                new ArticleNumber(ARTICLENUMBER1),
                new Price(PRICE),
                List.of(SERVICE1, SERVICE2));

        Product product2 = new Product(
                ID2,
                new ArticleNumber(ARTICLENUMBER2),
                new Price(PRICE),
                List.of(SERVICE1, SERVICE3));

        productStore.saveProduct(product1);

        productStore.saveProduct(product2);


        final List<Service> serviceList = productStore.findAllServices();

        assertThat(serviceList.size()).isEqualTo(2);
    }
}
