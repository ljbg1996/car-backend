package Entities;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

//todo
@Entity
@Setter

public class  Product {
    @Id
    final long id;
    final ArticleNumber articleNumber;
    double price;
    @ManyToMany
    final List<Service> includedServices;
    public Product(long id, ArticleNumber articleNumber, List<Service> includedServices){

        this.id = id;
        this.articleNumber = articleNumber;
        this.includedServices = includedServices;
    }


}
record ArticleNumber(long articleNumber) {
}




