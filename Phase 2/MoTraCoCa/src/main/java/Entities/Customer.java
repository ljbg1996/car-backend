package Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private final long id;
    //paymentInfo
    @OneToMany
    private List<Vehicle> vehicleList;
    @OneToMany
    private List<Order> orderList;


}
