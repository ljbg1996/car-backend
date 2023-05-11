package Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private final long id;

    @Column(name = "Payment information")
    String PaymentInfo;

    @OneToMany
    private List<Vehicle> vehicleList = new ArrayList<>();

    @OneToMany
    private List<Order> orderList = new ArrayList<>();


}
