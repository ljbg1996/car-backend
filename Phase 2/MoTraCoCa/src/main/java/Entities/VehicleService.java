package Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class VehicleService {

    private final long id;
    private final Service implementedService;
    private final List<UsageRight> usageRightList;

}
