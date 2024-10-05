package SE322.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 4000) // we can have a description of the category with maximum 4000 characters
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
