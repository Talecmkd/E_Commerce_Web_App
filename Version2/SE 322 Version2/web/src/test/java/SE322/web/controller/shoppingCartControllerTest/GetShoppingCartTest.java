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
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class GetShoppingCartTest {

    @Mock
    private ShoppingCartService shoppingCartService;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest req;

    private ShoppingCartController shoppingCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shoppingCartController = new ShoppingCartController(shoppingCartService, null);
    }

    @Test
    void testGetShoppingCartPage() {
        // Preparing test data
        User user = new User("admin", "admin", "John", "Doe", null, Role.ROLE_USER);
        ShoppingCart shoppingCart = new ShoppingCart(user);
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        shoppingCarts.add(shoppingCart);

        // Mocking HttpServletRequest
        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.getActiveShoppingCart(anyString())).thenReturn(shoppingCart);
        when(shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId())).thenReturn(new ArrayList<>());

        // Calling the method under test
        String viewName = shoppingCartController.getShoppingCartPage(null, req, model);

        // Asserting the view name
        assertEquals("master-template", viewName);
    }

    @Test
    void testGetShoppingCartPageWithNegative() {
        // Preparing test data
        User user = new User("username", "password", "John", "Doe", null, Role.ROLE_USER);
        ShoppingCart shoppingCart = new ShoppingCart(user);
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        shoppingCarts.add(shoppingCart);

        // Mocking HttpServletRequest
        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.getActiveShoppingCart(anyString())).thenReturn(shoppingCart);
        when(shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId())).thenReturn(new ArrayList<>());

        // Calling the method under test with an error
        String viewName = shoppingCartController.getShoppingCartPage("Error message", req, model);

        // Asserting the view name
        assertEquals("master-template", viewName);
    }
}
