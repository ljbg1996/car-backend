package com.motracoca.store;

import com.motracoca.entities.OrderEntity;
import com.motracoca.model.Order;
import com.motracoca.model.Price;
import com.motracoca.model.ProductConfiguration;

import java.util.List;
import java.util.stream.Collectors;

import static com.motracoca.store.VehicleStore.convertToVehicle;

public class OrderStore {
    public static Order convertToOrder(OrderEntity orderEntity) {
        List<ProductConfiguration> productConfigurations = orderEntity.getProducts().stream()
                .map(ProductConfigurationStore::convertToProductConfiguration)
                .collect(Collectors.toList());

        return new Order(
                orderEntity.getId(),
                orderEntity.isPayed(),
                convertToVehicle(orderEntity.getVehicleEntity()),
                new Price(orderEntity.getTotalPrice()),
                orderEntity.getDate(),
                productConfigurations
        );
    }
}
