package com.motracoca.services;

import com.motracoca.model.Customer;
import com.motracoca.model.Product;
import com.motracoca.model.ProductConfiguration;
import com.motracoca.store.CustomerStore;
import com.motracoca.store.ProductStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerStore cs;
    private final ProductStore ps;


    public void buy(List<String> articleNumberList, String vin, long customerId, int duration) {

        Customer c = cs.getCustomerById(customerId);
        List<Product> products = new ArrayList<>();

        for (String articleNumber : articleNumberList) {
            Product p = ps.getProductByArticleNumber(articleNumber);
            products.add(p);
        }

        List<ProductConfiguration> productConfigurationList = new ArrayList<>();

        for (Product p : products) {

            ProductConfiguration pc = new ProductConfiguration(p, duration);
            productConfigurationList.add(pc);

        }









    }
}
