package com.motracoca.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    private boolean isPayed;

    private LocalDate paymentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleEntity vehicleEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerEntity customerEntity;

    private double totalPrice;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<ProductConfigurationEntity> products;

    private boolean isCanceled;

    private LocalDate cancellationDate;



}


