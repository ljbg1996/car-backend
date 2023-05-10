package Entities;

import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity

public record UsageRight(long id, Date startDate,Date endDate,Product product ) {
    @Id
    long id;
    //todo

}
