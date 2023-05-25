package com.motracoca.store;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
