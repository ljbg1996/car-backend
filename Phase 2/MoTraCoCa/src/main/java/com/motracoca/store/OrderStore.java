package com.motracoca.store;

import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.ProductConfigurationEntity;
import com.motracoca.model.Order;
import com.motracoca.model.Price;
import com.motracoca.model.ProductConfiguration;
import com.motracoca.repositorys.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.motracoca.store.CustomerStore.convertToCustomer;
import static com.motracoca.store.CustomerStore.convertToCustomerEntity;
import static com.motracoca.store.VehicleStore.convertToVehicle;
import static com.motracoca.store.VehicleStore.convertToVehicleEntity;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStore {

    @Autowired
    private final OrderRepository or;

    public static Order convertToOrder(OrderEntity orderEntity) {
        List<ProductConfiguration> productConfigurations = orderEntity.getProducts().stream()
                .map(ProductConfigurationStore::convertToProductConfiguration)
                .collect(Collectors.toList());

        return new Order(
                orderEntity.getId(),
                orderEntity.isPayed(),
                orderEntity.getDate(),
                convertToVehicle(orderEntity.getVehicleEntity()),
                convertToCustomer(orderEntity.getCustomerEntity()),
                new Price(orderEntity.getTotalPrice()),
                orderEntity.getDate(),
                productConfigurations,
                orderEntity.isCanceled()
        );
    }

    public static OrderEntity convertToOrderEntity(Order order) {
        List<ProductConfigurationEntity> productConfigurationEntities = order.getProducts().stream()
                .map(ProductConfigurationStore::convertToProductConfigurationEntity)
                .collect(Collectors.toList());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setPayed(order.isPayed());
        orderEntity.setPaymentDate(order.getDate());
        orderEntity.setVehicleEntity(convertToVehicleEntity(order.getVehicle()));
        orderEntity.setCustomerEntity(convertToCustomerEntity(order.getCustomer()));
        orderEntity.setTotalPrice(order.getTotalPrice().price());
        orderEntity.setDate(order.getDate());
        orderEntity.setProducts(productConfigurationEntities);
        orderEntity.setCanceled(order.isCanceled());

        return orderEntity;
    }


    public void saveOrder(Order actualOrder) {

        OrderEntity orderEntity = convertToOrderEntity(actualOrder);
        or.save(orderEntity);

    }

    //TODO Optionials benutzen

    public Order getOrderById(long id) {

        OrderEntity orderEntity= or.getReferenceById(id);
        Order order = convertToOrder(orderEntity);

        if (orderEntity != null) {
            log.info("Retrieved order with ID{}", order.getId());
            return order;
        } else {
            log.warn("No order found with ID {}", id);
            return null;
        }
    }

    public void updateOrder(Order order){

        OrderEntity orderFromDB = or.getReferenceById(order.getId());
        OrderEntity orderUpdate = convertToOrderEntity(order);

        if (orderFromDB != null) {
            log.info("Order will be updated with ID{}", orderFromDB.getId());

            orderFromDB.setPayed(orderUpdate.isPayed());
            orderFromDB.setPaymentDate(orderUpdate.getPaymentDate());
            orderFromDB.setCanceled(orderUpdate.isCanceled());
            orderFromDB.setCancellationDate(orderUpdate.getCancellationDate());
            orderFromDB.setVehicleEntity(orderUpdate.getVehicleEntity());
            orderFromDB.setCustomerEntity(orderUpdate.getCustomerEntity());
            orderFromDB.setTotalPrice(orderUpdate.getTotalPrice());
            orderFromDB.setProducts(orderUpdate.getProducts());
            orderFromDB.setDate(orderFromDB.getDate());

            or.save(orderFromDB);

        } else {
            log.warn("No order found to be updated with ID {}", order.getId());

        }





    }

}
