package SE322.web.controller.shoppingCartControllerTest;

import SE322.model.Role;
import SE322.model.ShoppingCart;
import SE322.model.User;
import SE322.service.ShoppingCartService;
import SE322.web.controller.ShoppingCartController;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddProductToShoppingCartTest {

    @Mock
    private ShoppingCartService shoppingCartService;

    @Mock
    private HttpServletRequest req;

    private ShoppingCartController shoppingCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shoppingCartController = new ShoppingCartController(shoppingCartService, null);
    }

    @Test
    void testAddProductToShoppingCart() {
        // Preparing test data
        User user = new User("username", "password", "John", "Doe", null, Role.ROLE_USER);
        ShoppingCart shoppingCart = new ShoppingCart(user);

        // Mocking HttpServletRequest
        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.addProductToShoppingCart(anyString(), anyLong())).thenReturn(shoppingCart);

        // Calling the method under test
        String viewName = shoppingCartController.addProductToShoppingCart(1L, req);

        // Asserting the view name
        assertEquals("redirect:/shopping-cart", viewName);
    }

    @Test
    void testAddProductToShoppingCartWithException() {
        // Mocking HttpServletRequest
        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.addProductToShoppingCart(anyString(), anyLong())).thenThrow(new RuntimeException("Test exception"));

        // Calling the method under test
        String viewName = shoppingCartController.addProductToShoppingCart(1L, req);

        // Asserting the view name
        assertEquals("redirect:/shopping-cart?error=Test exception", viewName);
    }
}
