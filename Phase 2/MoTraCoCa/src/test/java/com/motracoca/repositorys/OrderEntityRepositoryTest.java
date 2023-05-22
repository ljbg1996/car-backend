package com.motracoca.repositorys;

import static org.assertj.core.api.Assertions.assertThat;

import com.motracoca.entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
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

        List<ProductConfigurationEntity> productConfigurationEntityList1 = new ArrayList<>();
        productConfigurationEntityList1.add(pce1);

        OrderEntity order1 = new OrderEntity();
        order1.setProducts(productConfigurationEntityList1);
        order1.setPayed(true);

        VehicleEntity ve1 = new VehicleEntity();
        ve1.setServiceEntityList(serviceList);

        order1.setVehicleEntity(ve1);
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
