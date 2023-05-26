package com.motracoca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ServiceEntity> includedServices;


}
