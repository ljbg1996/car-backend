package com.motracoca.repositorys;

import com.motracoca.entities.*;
import com.motracoca.model.*;
import com.motracoca.store.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductEntityRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ProductStore ps;
    @Autowired
    private ServiceStore ss;

    private Service safedService1;
    private Service safedService2;
    private Service safedService3;
    private Product safedProduct1;
    private Product safedProduct2;


    @BeforeEach
    public void init() {

        Price pricePerMonth1 = new Price(15.99);
        Price pricePerMonth2 = new Price(12.99);
        Service s1 = new Service(0L, "service1");
        Service s2 = new Service(0L, "service2");
        Service s3 = new Service(0L, "service3");

        safedService1 = ss.safeService(s1);
        safedService2 = ss.safeService(s2);
        safedService3 = ss.safeService(s3);

        List<Service> serviceList1 = new ArrayList<>();
        List<Service> serviceList2 = new ArrayList<>();
        serviceList1.add(safedService1);
        serviceList1.add(safedService2);
        serviceList1.add(safedService3);
        serviceList2.add(safedService1);
        serviceList2.add(safedService3);

        ArticleNumber an1 = new ArticleNumber(123L);
        ArticleNumber an2 = new ArticleNumber(456L);
        Product p1 = new Product(0L, an1, pricePerMonth1, serviceList1);
        Product p2 = new Product(0L, an2, pricePerMonth2, serviceList2);

        safedProduct1 = ps.saveProduct(p1);
        safedProduct2 = ps.saveProduct(p2);


    }
    @AfterEach
    public void cleanup() {
        productRepository.deleteAll();
    }

    @Test
    public void productEntityTest() {

        List<ProductEntity> productEntityList = productRepository.findAll();

        assertThat(productEntityList.size()).isNotNull();
        assertThat(productEntityList.size()).isEqualTo(2);
        assertThat(productEntityList.get(0).getIncludedServices().size()).isEqualTo(3);
        assertThat(productEntityList.get(0).getArticleNumber()).isEqualTo(123L);
        assertThat(productEntityList.get(0).getIncludedServices().get(1).getName()).isEqualTo("service2");
    }


}
