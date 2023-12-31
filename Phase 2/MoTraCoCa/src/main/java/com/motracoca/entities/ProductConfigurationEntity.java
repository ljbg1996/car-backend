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
     @Column(nullable = false)
     private long id;

     @ManyToOne
     private ProductEntity productEntity;

     private int duration;




}
