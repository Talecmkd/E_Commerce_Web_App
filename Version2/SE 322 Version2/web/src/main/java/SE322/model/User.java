package SE322.model;

import SE322.model.embeddables.UserAddress;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shop_users")
public class User implements UserDetails {

    @Id
    private String username;

    private String password;


    @OneToMany(fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride( name = "country", column = @Column(name = "user_country")),
        @AttributeOverride( name = "city", column = @Column(name = "user_city")),
        @AttributeOverride( name = "address1", column = @Column(name = "user_address1")),
        @AttributeOverride( name = "address2", column = @Column(name = "user_address2"))
    })
    private UserAddress userAddress;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired =  true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)  //it perceives the enumeration as a string instead of a number
    private Role role;


    public User(String username, String password, String name, String surname, UserAddress userAddress, Role role) {
        this.username = username;
        this.password = password;
        this.userAddress = userAddress;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }
    //returns the role of the User as an authority as explained in the Role Enum Class

}
