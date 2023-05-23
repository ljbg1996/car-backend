package com.motracoca.store;

import com.motracoca.entities.ProductEntity;
import com.motracoca.repositorys.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceStoreTest {


    @Mock
    private ProductRepository productRepository;

    private ProductStore productStore;
    private ServiceStore serviceStore;

    ProductEntity productEntity;

    @BeforeEach
    void init() {
        productEntity = new ProductEntity();
        productEntity.setId(12345789);
        productEntity.setArticleNumber(12345666);
        productEntity.setPrice(10);
        productEntity.setIncludedServices(new ArrayList<>());

//        productStore = new ProductStore(productRepository);

    }
//
//    @Test
//    void findUser() {
//        // given
//        when(userEntityRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
//        // when
//        final User user = userStore.findUser("U12345789");
//        // then
//        assertThat(user.getFirstName()).isEqualTo("Max");
//    }
}