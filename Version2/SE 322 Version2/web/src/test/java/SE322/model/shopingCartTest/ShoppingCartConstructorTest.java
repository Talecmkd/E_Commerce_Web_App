package SE322.model.shopingCartTest;
import SE322.model.ShoppingCart;
import SE322.model.User;
import SE322.model.enumerations.ShoppingCartStatus;
import junit.framework.TestCase;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ShoppingCartConstructorTest extends TestCase {

    User user;
    ShoppingCart shoppingCart;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        user = new User("username", "password", "John", "Doe", null, null);
        shoppingCart = new ShoppingCart(user);
    }

    public void testShoppingCartConstructor() {
        assertEquals(user, shoppingCart.getUser());
        assertEquals(ShoppingCartStatus.CREATED, shoppingCart.getStatus());
    }

    public void testShoppingCartConstructorNegative() {
        User anotherUser = new User("anotherUsername", "password", "Jane", "Doe", null, null);
        assertNotEquals(anotherUser, shoppingCart.getUser());
        assertNotEquals(ShoppingCartStatus.CANCELED, shoppingCart.getStatus());
    }
}
