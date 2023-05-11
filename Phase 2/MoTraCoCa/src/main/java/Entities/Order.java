package Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Getter
@RequiredArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private final long id;
    private boolean isPayed;
    @OneToOne
    private final Vehicle vehicle;
    private Price totalPrice;
    private final Date startDate;
    //OrderItem als eigene Klasse

    //@Embedded Product/Duration?
}

record Price(int price){}
