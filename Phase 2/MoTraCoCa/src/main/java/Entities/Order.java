package Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

    @Embedded
    private Price totalPrice;

    private final Date date;

    @OneToMany
    private final List<ProductConfiguration> products;

}

@Embeddable
record Price(int price){
    public Price() {
        this(0);
    }
}
