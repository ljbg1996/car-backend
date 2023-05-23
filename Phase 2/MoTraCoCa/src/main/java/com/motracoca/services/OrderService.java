package com.motracoca.services;

import com.motracoca.model.*;
import com.motracoca.store.CustomerStore;
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

    private final CustomerStore cs;
    private final ProductStore ps;
    private final VehicleStore vs;


    public void buy(List<String> articleNumberList, String vin, long customerId, int duration) {

        Customer c = cs.getCustomerById(customerId);
        List<Product> products = new ArrayList<>();

        for (String articleNumber : articleNumberList) {
            Product p = ps.getProductByArticleNumber(articleNumber);
            products.add(p);
        }

        List<ProductConfiguration> productConfigurationList = new ArrayList<>();
        double totalPrice = 0;

        for (Product p : products) {

            ProductConfiguration pc = new ProductConfiguration(p, duration);
            productConfigurationList.add(pc);
            totalPrice = totalPrice + p.getPrice();

        }

        Vehicle v1 = vs.getVehicleByVin(vin);
        LocalDate date = LocalDate.now();

        Order actualOrder = new Order(false, v1, totalPrice, date, productConfigurationList);


    }
}
