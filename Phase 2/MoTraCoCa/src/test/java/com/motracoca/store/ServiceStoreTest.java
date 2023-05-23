package com.motracoca.store;

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

    private ServiceStore serviceStore;
//
//    UserEntity userEntity;
//
//    @BeforeEach
//    void init() {
//        userEntity = new UserEntity();
//        userEntity.setId("U12345789");
//        userEntity.setFirstName("Max");
//        userEntity.setLastName("Mustermann");
//        userEntity.setVehicles(new ArrayList<>());
//
//        userStore = new UserStore(userEntityRepository);
//    }
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