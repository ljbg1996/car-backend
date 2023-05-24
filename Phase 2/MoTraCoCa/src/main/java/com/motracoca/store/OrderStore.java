package com.motracoca.store;

import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.ProductConfigurationEntity;
import com.motracoca.model.Order;
import com.motracoca.model.Price;
import com.motracoca.model.ProductConfiguration;

import java.util.List;
import java.util.stream.Collectors;

import static com.motracoca.store.VehicleStore.convertToVehicle;
import static com.motracoca.store.VehicleStore.convertToVehicleEntity;

public class OrderStore {
    public static Order convertToOrder(OrderEntity orderEntity) {
        List<ProductConfiguration> productConfigurations = orderEntity.getProducts().stream()
                .map(ProductConfigurationStore::convertToProductConfiguration)
                .collect(Collectors.toList());

        return new Order(
                orderEntity.isPayed(),
                convertToVehicle(orderEntity.getVehicleEntity()),
                new Price(orderEntity.getTotalPrice()),
                orderEntity.getDate(),
                productConfigurations
        );
    }

    public static OrderEntity convertToOrderEntity(Order order) {
        List<ProductConfigurationEntity> productConfigurationEntities = order.getProducts().stream()
                .map(ProductConfigurationStore::convertToProductConfigurationEntity)
                .collect(Collectors.toList());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setPayed(order.isPayed());
        orderEntity.setVehicleEntity(convertToVehicleEntity(order.getVehicle()));
        orderEntity.setTotalPrice(order.getTotalPrice().price());
        orderEntity.setDate(order.getDate());
        orderEntity.setProducts(productConfigurationEntities);

        return orderEntity;
    }
}
