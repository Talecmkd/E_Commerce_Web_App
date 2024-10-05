package SE322.web.controller.shoppingCartControllerTest;

import SE322.model.ShoppingCart;
import SE322.model.enumerations.ShoppingCartStatus;
import SE322.service.ShoppingCartService;
import SE322.web.controller.ShoppingCartController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UpdateShoppingCartTest {

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
    void testUpdateShoppingCart() {
        // Mocking ShoppingCartService
        ShoppingCart cart = new ShoppingCart();
        when(shoppingCartService.findById(anyLong())).thenReturn(Optional.of(cart));

        // Calling the method under test
        String viewName = shoppingCartController.updateShoppingCart(ShoppingCartStatus.CREATED, 1L, null);

        // Verifying the interaction with ShoppingCartService
        verify(shoppingCartService, times(1)).findById(anyLong());
        verify(shoppingCartService, times(1)).save(any());

        // Asserting the view name
        assert viewName.equals("redirect:/shopping-cart");
    }

    @Test
    void testUpdateShoppingCartWithNullCart() {
        // Mocking ShoppingCartService
        when(shoppingCartService.findById(anyLong())).thenReturn(Optional.empty());

        // Calling the method under test
        String viewName = shoppingCartController.updateShoppingCart(ShoppingCartStatus.CREATED, 1L, null);

        // Verifying the interaction with ShoppingCartService
        verify(shoppingCartService, times(1)).findById(anyLong());
        verify(shoppingCartService, never()).save(any());

        // Asserting the view name
        assert viewName.equals("redirect:/shopping-cart");
    }
}
