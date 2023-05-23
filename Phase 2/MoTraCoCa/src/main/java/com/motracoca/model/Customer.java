package com.motracoca.model;

import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.VehicleEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Customer {


    private final long id;
    private final List<Vehicle> vehicleList;
    private final List<Order> orderList;
    private final String PaymentInfo;


}
