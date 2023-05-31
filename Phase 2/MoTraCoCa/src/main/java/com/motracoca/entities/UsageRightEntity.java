package com.motracoca.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class UsageRightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private ServiceEntity coveredService;

    @ManyToOne(cascade = CascadeType.ALL)
    private VehicleEntity coveredVehicle;

    @ManyToOne(cascade = CascadeType.ALL)
    private CustomerEntity coveredCustomer;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductEntity fromProduct;

    @ManyToOne(cascade = CascadeType.ALL)
    private OrderEntity fromOrder;


}
