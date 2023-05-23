package com.motracoca.services;

public class OrderService {

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
