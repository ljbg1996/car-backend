package com.motracoca.motracoca.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class UsageRightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate endDate;

    @OneToOne
    private ProductEntity product;


}
