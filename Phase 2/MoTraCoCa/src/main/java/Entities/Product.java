package Entities;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//todo
@Entity
@Setter
public class  Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    final long id;

    @Column(name = "Article number")
    final ArticleNumber articleNumber;

    @Column(name = "Price per month")
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




