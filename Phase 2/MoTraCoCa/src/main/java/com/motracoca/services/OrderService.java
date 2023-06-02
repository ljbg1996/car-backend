package com.motracoca.services;

import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.UsageRightEntity;
import com.motracoca.model.*;
import com.motracoca.repositorys.UsageRightRepository;
import com.motracoca.store.OrderStore;
import com.motracoca.store.VehicleStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    @Autowired
    private OrderStore os;
    @Autowired
    private VehicleStore vs;

    @Autowired
    private UsageRightRepository usageRightRepository;


    public OrderEntity buy(List<ProductConfiguration> articleNumberDurationList, String vin) {

        Vehicle v = vs.getVehicleByVin(vin);
        Customer c = v.getOwner();
        double sum = 0;

        for (ProductConfiguration pc : articleNumberDurationList) {

            double productWithDurationPrice = (pc.product().getPrice().price() * pc.duration());
            sum += productWithDurationPrice;

        }

        Price totalPrice = new Price(sum);

        LocalDate date = LocalDate.now();
        LocalDate paymentDate = null;

        Order actualOrder = new Order(0L,false, paymentDate, v, c, totalPrice, date, articleNumberDurationList, false, null);

        OrderEntity savedOrder = os.saveOrder(actualOrder);

        processPayment(actualOrder);

        return savedOrder;

    }



    public void processPayment(Order order){

    }



    public Order cancelOrder(OrderEntity orderEntity){

        orderEntity.setCanceled(true);
        orderEntity.setCancellationDate(LocalDate.now());

        List<UsageRightEntity> onCancellationUsageRightList = usageRightRepository.findByFromOrder(orderEntity);
        for (UsageRightEntity ure : onCancellationUsageRightList) {

            usageRightRepository.deleteById(ure.getId());

        }

        Order updatedOrder = os.updateOrderEntity(orderEntity);
        return updatedOrder;

    }

}
