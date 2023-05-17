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


    @DisplayName("should store a order")
    @Test
    void storeOrderTest() {

        ServiceEntity se1 = new ServiceEntity();
        ServiceEntity se2 = new ServiceEntity();
        ProductEntity pe1 = new ProductEntity();
        List<ServiceEntity> serviceList = new ArrayList<>();
        serviceList.add(se1);
        serviceList.add(se2);
        pe1.setIncludedServices(serviceList);
        ProductConfigurationEntity pce1 = new ProductConfigurationEntity();
        pce1.setProduct(pe1);
        ProductConfigurationEntity pce2 = new ProductConfigurationEntity();
        pce2.setProduct(pe1);
        ProductConfigurationEntity pce3 = new ProductConfigurationEntity();
        pce3.setProduct(pe1);

        OrderEntity order1 = new OrderEntity();
        List<ProductConfigurationEntity> productConfigurationEntityList1 = new ArrayList<>();
        order1.setProducts(productConfigurationEntityList1);
        order1.getProducts().add(pce1);
        order1.getProducts().add(pce2);
        order1.getProducts().add(pce3);
        order1.setPayed(true);
        VehicleEntity ve1 = new VehicleEntity();
        order1.setVehicleEntity(ve1);
        order1.setTotalPrice(20.99);
        LocalDate date = LocalDate.now();
        order1.setDate(date);

        OrderEntity order2 = new OrderEntity();
        List<ProductConfigurationEntity> productConfigurationEntityList2 = new ArrayList<>();
        order2.setProducts(productConfigurationEntityList2);
        order2.getProducts().add(pce1);
        order2.getProducts().add(pce2);
        order2.setPayed(false);
        VehicleEntity ve2 = new VehicleEntity();
        order2.setVehicleEntity(ve2);
        order2.setTotalPrice(15.78);
        LocalDate date1 = LocalDate.now();
        order1.setDate(date1);

        or.save(order1);
        or.save(order2);

        final List<OrderEntity> orderList = or.findAll();

        assertThat(orderList.size()).isNotNull();
        assertThat(orderList.size()).isEqualTo(2);

        assertThat(orderList.get(0).getProducts().size()).isEqualTo(3);
        assertThat(orderList.get(1).getProducts().size()).isEqualTo(2);

        assertThat(orderList.get(0).isPayed()).isTrue();
        assertThat(orderList.get(1).isPayed()).isFalse();

        assertThat(orderList.get(0).getTotalPrice()).isEqualTo(20.99);
        assertThat(orderList.get(1).getTotalPrice()).isEqualTo(15.78);


    }




}
