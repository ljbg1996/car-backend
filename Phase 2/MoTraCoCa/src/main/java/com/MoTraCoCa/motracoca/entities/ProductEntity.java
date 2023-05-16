package com.motracoca.motracoca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "Article number")
    private long articleNumber;

    @Column(name = "Price per month")
    private double price;

    @ManyToMany
    private List<ServiceEntity> includedServices;

}
