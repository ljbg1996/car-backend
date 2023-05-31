package com.motracoca.repositorys;

import static org.assertj.core.api.Assertions.assertThat;

import com.motracoca.entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderEntityRepositoryTest {


    @Autowired
    private OrderRepository or;
    @Autowired
    private ServiceRepository sr;
    @Autowired
    private ProductRepository pr;

    @Autowired
    private VehicleRepository vr;

    @Autowired
    private CustomerRepository cr;


    @DisplayName("should store a order")
    @Test
    public void storeOrderTest() {

        ServiceEntity se1 = new ServiceEntity();
        ServiceEntity se2 = new ServiceEntity();
        ServiceEntity savedServiceEntity1 = sr.save(se1);
        ServiceEntity savedServiceEntity2 = sr.save(se2);

        List<ServiceEntity> serviceList = new ArrayList<>();
        serviceList.add(savedServiceEntity1);
        serviceList.add(savedServiceEntity2);

        ProductEntity pe1 = new ProductEntity();
        pe1.setIncludedServices(serviceList);

        ProductEntity safedProductEntity = pr.save(pe1);

        ProductConfigurationEntity pce1 = new ProductConfigurationEntity();
        pce1.setProductEntity(safedProductEntity);
        pce1.setDuration(6);

        List<ProductConfigurationEntity> productConfigurationEntityList1 = new ArrayList<>();
        productConfigurationEntityList1.add(pce1);

        VehicleEntity ve1 = new VehicleEntity();
        ve1.setServiceEntityList(serviceList);

        VehicleEntity safedVehicleEntity = vr.save(ve1);

        OrderEntity order1 = new OrderEntity();
        order1.setProducts(productConfigurationEntityList1);
        order1.setPayed(true);
        order1.setVehicleEntity(safedVehicleEntity);
        order1.setTotalPrice(20.99);
        LocalDate date = LocalDate.now();
        order1.setDate(date);



        or.save(order1);

        final List<OrderEntity> orderList = or.findAll();

        assertThat(orderList.size()).isNotNull();
        assertThat(orderList.size()).isEqualTo(1);

        assertThat(orderList.get(0).getProducts().size()).isEqualTo(1);

        assertThat(orderList.get(0).isPayed()).isTrue();

        assertThat(orderList.get(0).getTotalPrice()).isEqualTo(20.99);



    }




}
