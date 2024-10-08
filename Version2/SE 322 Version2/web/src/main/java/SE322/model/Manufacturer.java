package SE322.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="manufacturers") // the table name of the database
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // the ID is automatically generated by the database
    private Long id;

    private String name;

    @Column(name = "manufacturer_address") // the column for the address will be named manufacturer_address
    private String address;

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
