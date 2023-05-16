package com.MoTraCoCa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToMany( cascade= CascadeType.ALL )
    private List<VehicleEntity> vehicleEntityList = new ArrayList<>();

    @OneToMany( cascade= CascadeType.ALL )
    private List<OrderEntity> orderEntityList;

    @Column(name = "Payment information")
    String PaymentInfo;



}
