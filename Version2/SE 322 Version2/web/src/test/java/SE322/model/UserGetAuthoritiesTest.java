package SE322.model;

import SE322.model.embeddables.UserAddress;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import static SE322.model.Role.ROLE_USER;



public class UserGetAuthoritiesTest extends TestCase {
    protected UserAddress ua;
    protected Role r;
    protected User u;

    @Override
    protected void setUp(){
        // Create objects that will be sent to the constructor of User
        ua = new UserAddress();
        r = ROLE_USER;

        // Create a user instance to test
        u = new User("username", "password", "realName", "realSurname", ua, r);
    }

    @Test
    public void testGetAuthoritiesPositive() {
        // Call the method to get authorities
        Collection<? extends GrantedAuthority> authorities = u.getAuthorities();

        // Assert that the authorities list contains exactly one element which is the user's role
        assertEquals(1, authorities.size());
        assertEquals(Role.ROLE_USER, authorities.iterator().next());
    }
    @Test
    public void testgetAuthoritiesNegative() {

        // Call the method to get authorities
        Collection<? extends GrantedAuthority> authorities = u.getAuthorities();

        // Assert that the authorities list is empty
        assertFalse(authorities.isEmpty());
    }
}