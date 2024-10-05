package SE322.web.controller.shoppingCartControllerTest;

import SE322.model.ShoppingCart;
import SE322.service.ShoppingCartService;
import SE322.web.controller.ShoppingCartController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetFilterShoppingCartsPageTest {

    @Mock
    private ShoppingCartService shoppingCartService;

    @Mock
    private Model model;

    private ShoppingCartController shoppingCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shoppingCartController = new ShoppingCartController(shoppingCartService, null);
    }

    @Test
    void testGetFilterShoppingCartsPage() {
        // Preparing test data
        List<ShoppingCart> carts = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();
        carts.add(shoppingCart);

        // Mocking ShoppingCartService
        when(shoppingCartService.findAll()).thenReturn(carts);

        // Calling the method under test
        String viewName = shoppingCartController.getFilterShoppingCartsPage(model);

        // Asserting the view name
        assertEquals("master-template", viewName);
    }

    @Test
    void testGetFilterShoppingCartsPageWithEmptyList() {
        // Preparing test data with an empty list of shopping carts
        List<ShoppingCart> emptyList = new ArrayList<>();

        // Mocking ShoppingCartService
        when(shoppingCartService.findAll()).thenReturn(emptyList);

        // Calling the method under test
        String viewName = shoppingCartController.getFilterShoppingCartsPage(model);

        // Asserting the view name
        assertEquals("master-template", viewName);
    }
}
