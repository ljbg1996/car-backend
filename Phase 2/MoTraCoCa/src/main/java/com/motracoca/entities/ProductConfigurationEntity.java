package com.motracoca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductConfigurationEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "id", nullable = false)
     private long id;

     @ManyToOne(cascade = CascadeType.ALL)
     private ProductEntity productEntity;

     private int duration;
}
