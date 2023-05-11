package Entities;

import javax.persistence.Entity;
import java.util.Date;

@Entity

public record UsageRight(long id, Date startDate,Date endDate,Product product ) {
//    @Id
//    long id;
    //todo

}
