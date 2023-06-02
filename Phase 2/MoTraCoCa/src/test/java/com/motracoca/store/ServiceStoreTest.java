package com.motracoca.store;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.Service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ServiceStoreTest {

    ServiceEntity serviceEntity;

    @BeforeEach
    void init() {
        serviceEntity = new ServiceEntity();
        serviceEntity.setId(12345678);
        serviceEntity.setName("TestService1");

    }
    @Test
    public void convertEntityToModel(){
//        when
        Service service = ServiceStore.convertToService(serviceEntity);
//        then
        Assertions.assertThat(service.id()).isEqualTo(serviceEntity.getId());
    }

}
