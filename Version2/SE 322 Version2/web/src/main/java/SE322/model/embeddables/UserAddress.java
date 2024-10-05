package SE322.model.embeddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable // we use this to embedd this class into the entity table of the User class
public class UserAddress {
    private String country;
    private String city;
    private String address1;
    private String address2;
}
