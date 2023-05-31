package com.motracoca.repositorys;

import com.motracoca.entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UsageRightRepositoryTest {
    @Autowired
    private UsageRightRepository usageRightRepository;


    @DisplayName("should store a UsageRight")
    @Test
    public void storeUsageRightTest() {

        ServiceEntity se1 = new ServiceEntity();
        ServiceEntity se2 = new ServiceEntity();
        ServiceEntity se3 = new ServiceEntity();
        ServiceEntity se4 = new ServiceEntity();

        List<ServiceEntity> offeredServices = new ArrayList<>();
        offeredServices.add(se1);
        offeredServices.add(se2);
        offeredServices.add(se3);
        offeredServices.add(se4);

        List<ServiceEntity> includedServices = new ArrayList<>();
        includedServices.add(se1);
        includedServices.add(se2);
        includedServices.add(se4);

        ProductEntity pe1 = new ProductEntity();
        pe1.setIncludedServices(includedServices);
        pe1.setPrice(13.20);

        CustomerEntity c1 = new CustomerEntity();

        VehicleEntity v1 = new VehicleEntity();
        v1.setOwner(c1);
        v1.setServiceEntityList(offeredServices);

        ProductConfigurationEntity pce1 = new ProductConfigurationEntity();
        pce1.setProductEntity(pe1);
        pce1.setDuration(6);
        List<ProductConfigurationEntity> pceList = new ArrayList<>();
        pceList.add(pce1);

        OrderEntity o1 = new OrderEntity();
        o1.setVehicleEntity(v1);
        o1.setCustomerEntity(c1);
        o1.setProducts(pceList);

        UsageRightEntity ure = new UsageRightEntity();
        ure.setCoveredService(se1);
        ure.setCoveredVehicle(v1);
        ure.setCoveredCustomer(c1);
        ure.setFromProduct(pe1);
        ure.setFromOrder(o1);

        usageRightRepository.save(ure);

        List<UsageRightEntity> savedUsageRight = usageRightRepository.findAll();

        assertThat(savedUsageRight).isNotNull();
        assertThat(savedUsageRight.get(0).getFromOrder().getProducts().get(0).getProductEntity().getPrice()).isEqualTo(13.20);

    }
}
