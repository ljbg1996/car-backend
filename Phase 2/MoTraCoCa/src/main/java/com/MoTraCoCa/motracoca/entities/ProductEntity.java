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
    final long id;

    @Column(name = "Article number")
    final long articleNumber;

    @Column(name = "Price per month")
    double price;

    @ManyToMany
    final List<ServiceEntity> includedServices;

}
