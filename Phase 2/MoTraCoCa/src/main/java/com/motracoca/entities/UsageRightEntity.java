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

    @ManyToOne
    private ServiceEntity coveredService;

    @ManyToOne
    private VehicleEntity coveredVehicle;

    @ManyToOne
    private CustomerEntity coveredCustomer;

    @ManyToOne
    private ProductEntity fromProduct;

    @ManyToOne
    private OrderEntity fromOrder;


}
