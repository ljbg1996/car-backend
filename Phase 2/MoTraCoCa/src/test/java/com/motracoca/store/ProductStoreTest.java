package com.motracoca.store;

import com.motracoca.entities.ProductEntity;
import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.ArticleNumber;
import com.motracoca.model.Price;
import com.motracoca.model.Product;
import com.motracoca.model.Service;
import com.motracoca.repositorys.ProductRepository;
import com.motracoca.repositorys.ServiceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductStoreTest {


    private ServiceEntity SERVICEENTITY1 = new ServiceEntity();
    private ServiceEntity SERVICEENTITY2 = new ServiceEntity();
    private ServiceEntity SERVICEENTITY3 = new ServiceEntity();

    @Autowired
    ProductStore productStore;
    @Autowired
    ServiceStore serviceStore;



    @BeforeEach
    public void init() {
//        SERVICEENTITY1.setName("SERVICE1");
//        SERVICEENTITY2.setName("SERVICE2");
//        SERVICEENTITY3.setName("SERVICE3");
//
//        SERVICEENTITY1.setId(11111111L);
//        SERVICEENTITY2.setId(22222222L);
//        SERVICEENTITY3.setId(3333333L);
    }


    @Test
    public void getProductByArticleNumberTest() {
        //given
        long ARTICLENUMBER = 123456L;
        long ID = 0L;
        double PRICE = 420.0;


        Service SERVICE1 = new Service(0L, "Service1");
        Service SERVICE2 = new Service(0L, "Service2");

        Service safedService1 = serviceStore.safeService(SERVICE1);
        Service safedService2 = serviceStore.safeService(SERVICE2);

        Product product = new Product(
                ID,
                new ArticleNumber(ARTICLENUMBER),
                new Price(PRICE),
                List.of(safedService1, safedService2));

        productStore.saveProduct(product);


        // when
        Product productResult = productStore
                .findProductByArticleNumber(new ArticleNumber(ARTICLENUMBER));

        // then
        Assertions.assertThat(productResult
                        .getArticleNumber()
                        .articleNumber())
                .isEqualTo(ARTICLENUMBER);

//        sr.deleteById(safedService1.id());
//        sr.deleteById(safedService2.id());
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
        Service SERVICE1 = new Service(0L, "Service1");
        Service SERVICE2 = new Service(0L, "Service2");

        long ARTICLENUMBER = 8150815;
        long ID = 0L;
        double PRICE = 420.0;

        Service safedService1 = serviceStore.safeService(SERVICE1);
        Service safedService2 = serviceStore.safeService(SERVICE2);

//        ProductEntity productEntity = new ProductEntity();

        // when
        Product product1 = new Product(
                ID,
                new ArticleNumber(ARTICLENUMBER),
                new Price(PRICE),
                List.of(safedService1, safedService2));

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

//        sr.deleteById(safedService1.id());
//        sr.deleteById(safedService2.id());
    }

    @Test
    public void getServicesTest() {
//        given
        //TODO services vorher speichern
        Service SERVICE1 = new Service(0L, "Service1");
        Service SERVICE2 = new Service(0L, "Service2");
        Service SERVICE3 = new Service(0L, "Service3");

        long ARTICLENUMBER1 = 41516546L;
        long ARTICLENUMBER2 = 87451465L;
        long ID1 = 0L;
        long ID2 = 0L;
        double PRICE = 420.0;

        Service safedService1 = serviceStore.safeService(SERVICE1);
        Service safedService2 = serviceStore.safeService(SERVICE2);
        Service safedService3 = serviceStore.safeService(SERVICE3);


        Product product1 = new Product(
                ID1,
                new ArticleNumber(ARTICLENUMBER1),
                new Price(PRICE),
                List.of(safedService1, safedService2));

        Product product2 = new Product(
                ID2,
                new ArticleNumber(ARTICLENUMBER2),
                new Price(PRICE),
                List.of(safedService1, safedService3));

        productStore.saveProduct(product1);

        productStore.saveProduct(product2);


        List<Service> serviceList = productStore.findAllServices();


        assertThat(serviceList.size()).isEqualTo(3);
    }
}
