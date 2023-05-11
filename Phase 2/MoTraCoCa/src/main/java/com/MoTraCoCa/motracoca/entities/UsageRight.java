package com.motracoca.motracoca.entities;

import jakarta.persistence.*;
import java.util.Date;



public record UsageRight(long id, Date startDate,Date endDate,Product product ) {
    //todo

}
