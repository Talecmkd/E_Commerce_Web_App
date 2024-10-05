package SE322.web.controller.shoppingCartControllerTest;

import SE322.model.ShoppingCart;
import SE322.service.ShoppingCartService;
import SE322.web.controller.ShoppingCartController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FilterShoppingCartsTest {

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
    void testFilterShoppingCarts() {
        // Preparing test data
        LocalDateTime from = LocalDateTime.of(2024, 5, 1, 0, 0);
        LocalDateTime to = LocalDateTime.of(2024, 5, 2, 0, 0);
        List<ShoppingCart> filteredCarts = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();
        filteredCarts.add(shoppingCart);

        // Mocking ShoppingCartService
        when(shoppingCartService.filterByDateTimeBetween(from, to)).thenReturn(filteredCarts);

        // Calling the method under test
        String viewName = shoppingCartController.filterShoppingCarts(from, to, model);

        // Asserting the view name
        assertEquals("master-template", viewName);
    }

    @Test
    void testFilterShoppingCartsWithEmptyList() {
        // Preparing test data with an empty list of filtered shopping carts
        LocalDateTime from = LocalDateTime.of(2024, 5, 1, 0, 0);
        LocalDateTime to = LocalDateTime.of(2024, 5, 2, 0, 0);
        List<ShoppingCart> emptyList = new ArrayList<>();

        // Mocking ShoppingCartService
        when(shoppingCartService.filterByDateTimeBetween(from, to)).thenReturn(emptyList);

        // Calling the method under test
        String viewName = shoppingCartController.filterShoppingCarts(from, to, model);

        // Asserting the view name
        assertEquals("master-template", viewName);
    }
}
