package com.motracoca.services;

import com.motracoca.model.*;
import com.motracoca.store.CustomerStore;
import com.motracoca.store.OrderStore;
import com.motracoca.store.ProductStore;
import com.motracoca.store.VehicleStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private Order order;

    private final OrderStore os;
    private final VehicleStore vs;


    //TODO Rückgabewert
    public Order buy(List<ProductConfiguration> articleNumberDurationList, String vin) {


        Vehicle v = vs.getVehicleByVin(vin);
        Customer c = v.getOwner();


        double sum = 0;

        for (ProductConfiguration pc : articleNumberDurationList) {

            double productWithDurationPrice = (pc.product().getPrice().price() * pc.duration());
            sum += productWithDurationPrice;

        }

        Price totalPrice = new Price(sum);

        LocalDate date = LocalDate.now();


        // TODO id im order model nicht mehr final, da ich sie sonst hier im konstruktor setzen müsste
        Order actualOrder = new Order(false, v, c, totalPrice, date, articleNumberDurationList, false);

        order = actualOrder;

        os.saveOrder(actualOrder);

        return os.getOrder(actualOrder.getId());

    }
}
