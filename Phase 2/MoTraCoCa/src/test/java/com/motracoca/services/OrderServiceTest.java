package com.motracoca.services;


import com.motracoca.entities.CustomerEntity;
import com.motracoca.entities.OrderEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.*;
import com.motracoca.model.Order;
import com.motracoca.repositorys.*;
import com.motracoca.store.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderServiceTest {

    @Autowired
    private VehicleRepository vr;
    @Autowired
    private CustomerRepository cr;
    @Autowired
    private OrderRepository or;
    @Autowired
    private ProductRepository pr;
    @Autowired
    private ServiceRepository sr;
    @Autowired
    private UsageRightRepository urr;
    @Autowired
    private OrderService orderService = new OrderService();
    @Autowired
    private CustomerStore cs;
    @Autowired
    private ProductStore ps;
    @Autowired
    private VehicleStore vs;
    @Autowired
    private ServiceStore ss;

    private List<ProductConfiguration> articleNumberDurationList;
    private Vehicle v;
    private VehicleEntity safedVehicleEntity;

    private CustomerEntity safedCustomerEntitity;
    private Service safedService1;
    private Service safedService2;
    private Service safedService3;
    private Product safedProduct1;
    private Product safedProduct2;

    @BeforeEach
    public void init(){

        Price pricePerMonth1 = new Price(15.99);
        Price pricePerMonth2 = new Price(12.99);
        Service s1 = new Service(0L, "service1");
        Service s2 = new Service(0L, "service2");
        Service s3 = new Service(0L, "service3");

        safedService1 = ss.safeService(s1);
        safedService2 = ss.safeService(s2);
        safedService3 = ss.safeService(s3);

        List<Service> serviceList1 = new ArrayList<>();
        List<Service> serviceList2 = new ArrayList<>();
        serviceList1.add(safedService1);
        serviceList1.add(safedService2);
        serviceList1.add(safedService3);
        serviceList2.add(safedService1);
        serviceList2.add(safedService3);

        ArticleNumber an1 = new ArticleNumber(123L);
        ArticleNumber an2 = new ArticleNumber(456L);
        Product p1 = new Product(0L, an1, pricePerMonth1, serviceList1);
        Product p2 = new Product(0L, an2, pricePerMonth2, serviceList2);

        safedProduct1 = ps.saveProduct(p1);
        safedProduct2 = ps.saveProduct(p2);

        ProductConfiguration  pc1 = new ProductConfiguration(0L, safedProduct1, 3);
        ProductConfiguration  pc2 = new ProductConfiguration(0L, safedProduct2, 6);

        articleNumberDurationList = new ArrayList<>();
        articleNumberDurationList.add(pc1);
        articleNumberDurationList.add(pc2);

        Customer c = new Customer(0L, "payment");

        Customer safedCustomer = cs.saveCustomer(c);
        safedCustomerEntitity = CustomerStore.convertToCustomerEntity(safedCustomer);

        Vin vin = new Vin("vin123");
        v = new Vehicle(0L, vin, safedCustomer, serviceList1);

        safedVehicleEntity = vs.saveVehicle(v);

    }


    @DisplayName("should place a order and update the customer")
    @Test
    public void placeOrder(){

        OrderEntity savedOrder = orderService.buy(articleNumberDurationList, safedVehicleEntity.getVin());

        assertThat(savedOrder.getProducts().size()).isEqualTo(2);
        assertThat(savedOrder.getTotalPrice()).isEqualTo(125.91);
        assertThat(savedOrder.getProducts().get(0).getProductEntity().getIncludedServices().size()).isEqualTo(3);
        assertThat(savedOrder.getProducts().get(1).getProductEntity().getIncludedServices().size()).isEqualTo(2);
        assertThat(savedOrder.getProducts().get(0).getDuration()).isEqualTo(3);
        assertThat(savedOrder.getProducts().get(1).getDuration()).isEqualTo(6);

        or.deleteById(savedOrder.getId());
        vr.deleteById(safedVehicleEntity.getId());

    }

    @Test
    @DisplayName("should cancel an order")
    public void cancelOrderTest(){

        OrderEntity savedOrder = orderService.buy(articleNumberDurationList, safedVehicleEntity.getVin());

        Order canceledOrder = orderService.cancelOrder(savedOrder);

        assertThat(canceledOrder.isCanceled()).isTrue();
        assertThat(canceledOrder.getCancellationDate()).isNotNull();

    }



    @AfterEach
    public void cleanup() {
        or.deleteAll();
        vr.deleteAll();
        cr.deleteAll();
        pr.deleteAll();
        sr.deleteAll();
        urr.deleteAll();
    }


}

