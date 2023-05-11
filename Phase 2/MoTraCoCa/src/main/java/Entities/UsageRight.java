package Entities;

import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.*;
import java.util.Date;

@Value
@Entity
public class UsageRight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @OneToOne
    private Product product;

    public UsageRight() {
        this.id = 0L;
        this.startDate = null;
        this.endDate = null;
        this.product = null;
    }

    public UsageRight(Date startDate, Date endDate, Product product) {
        this.id = 0L;
        this.startDate = startDate;
        this.endDate = endDate;
        this.product = product;
    }
}
