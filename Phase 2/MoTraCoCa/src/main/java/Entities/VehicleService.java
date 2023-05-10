package Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class VehicleService {

    private final long id;
    private final Service implementedService;
    private final List<UsageRight> usageRightList;

}
