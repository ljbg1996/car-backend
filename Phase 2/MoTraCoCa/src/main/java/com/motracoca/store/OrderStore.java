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
    private OrderRepository or;

    public static Order convertToOrder(OrderEntity orderEntity) {
        List<ProductConfiguration> productConfigurations = orderEntity.getProducts().stream()
                .map(ProductConfigurationStore::convertToProductConfiguration)
                .collect(Collectors.toList());

        return new Order(
                orderEntity.getId(),
                orderEntity.isPayed(),
                orderEntity.getPaymentDate(),
                convertToVehicle(orderEntity.getVehicleEntity()),
                convertToCustomer(orderEntity.getCustomerEntity()),
                new Price(orderEntity.getTotalPrice()),
                orderEntity.getDate(),
                productConfigurations,
                orderEntity.isCanceled(),
                orderEntity.getCancellationDate()
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


    public OrderEntity saveOrder(Order actualOrder) {

        OrderEntity orderEntity = convertToOrderEntity(actualOrder);
        OrderEntity savedEntity = or.save(orderEntity);

        return savedEntity;

    }

    //TODO Optionials benutzen


    public Order updateOrderEntity(OrderEntity orderEntity) {

        Optional<OrderEntity> orderEntityOptional = or.findById(orderEntity.getId());
        OrderEntity orderFromDB = orderEntityOptional.get();
        Order updatedOrder;

        if (orderFromDB != null) {
            log.info("Order will be updated with ID{}", orderEntity.getId());


            orderFromDB.setPayed(orderEntity.isPayed());
            orderFromDB.setPaymentDate(orderEntity.getPaymentDate());
            orderFromDB.setCanceled(orderEntity.isCanceled());
            orderFromDB.setCancellationDate(orderEntity.getCancellationDate());
            orderFromDB.setVehicleEntity(orderEntity.getVehicleEntity());
            orderFromDB.setCustomerEntity(orderEntity.getCustomerEntity());
            orderFromDB.setTotalPrice(orderEntity.getTotalPrice());
            orderFromDB.setProducts(orderEntity.getProducts());
            orderFromDB.setDate(orderEntity.getDate());


        }
        updatedOrder = OrderStore.convertToOrder(or.save(orderFromDB));
        return updatedOrder;

        }


}







