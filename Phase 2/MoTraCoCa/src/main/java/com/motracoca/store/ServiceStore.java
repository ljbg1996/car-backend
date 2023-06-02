package com.motracoca.store;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.model.Service;
import com.motracoca.repositorys.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceStore {

    @Autowired
    private ServiceRepository sr;

    public static Service convertToService(ServiceEntity serviceEntity) {
        //Added this
        if (serviceEntity == null) {
            return null;
        }

        return new Service(serviceEntity.getId(), serviceEntity.getName());
    }

    public static ServiceEntity convertToServiceEntity(Service service) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(service.id());
        serviceEntity.setName(service.name());
        return serviceEntity;
    }

    public Service safeService(Service service){

        ServiceEntity serviceEntity = convertToServiceEntity(service);
        ServiceEntity savedServiceEntity = sr.save(serviceEntity);
        Service savedService = convertToService(savedServiceEntity);

        return savedService;

    }
}
