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
@Entity
public class VehicleService {

    @Id
    private Long id;
    @ManyToOne
    private Service implementedService;
    @OneToMany(mappedBy = "vehicleService")
    private List<UsageRight> usageRights; // entspricht aktuell nicht den Anforderungen
    @ManyToOne
    private Vehicle vehicle;  // ? is not in class diagramm
    // constructor, getters and setters

}
