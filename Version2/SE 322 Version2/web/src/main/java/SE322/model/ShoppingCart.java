package SE322.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import SE322.model.enumerations.ShoppingCartStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // the ID is automatically generated by the database
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //the format the date will be showed in
    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "shopping_cart_products") // the name of the table referencing the shopping cart and product tables
    private List<Product> products;

    @Enumerated(EnumType.STRING) //it perceives the enumeration as a string instead of a number
    private ShoppingCartStatus status;

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
