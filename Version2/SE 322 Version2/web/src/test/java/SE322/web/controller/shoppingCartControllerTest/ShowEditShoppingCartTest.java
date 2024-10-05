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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ShowEditShoppingCartTest {

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
    void testShowEditShoppingCart() {
        // Preparing test data
        User user = new User("username", "password", "John", "Doe", null, Role.ROLE_USER);
        ShoppingCart shoppingCart = new ShoppingCart(user);

        // Mocking HttpServletRequest
        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.getActiveShoppingCart("username")).thenReturn(shoppingCart);

        // Calling the method under test
        String viewName = shoppingCartController.showEditShoppingCart(req, model);

        // Asserting the view name
        assertEquals("master-template", viewName);
    }

    @Test
    void testShowEditShoppingCartWithNullUser() {
        // Mocking HttpServletRequest
        when(req.getRemoteUser()).thenReturn(null);

        // Calling the method under test
        String viewName = shoppingCartController.showEditShoppingCart(req, model);

        // Asserting the view name
        assertEquals("master-template", viewName);
    }
}
