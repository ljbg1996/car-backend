package com.motracoca.store;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.Service;

public class ServiceStore {
    public static Service convertToService(ServiceEntity serviceEntity) {
        return new Service(serviceEntity.getId(), serviceEntity.getName());
    }
}
