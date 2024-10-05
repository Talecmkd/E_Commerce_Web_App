package SE322.service;

import SE322.model.Product;
import SE322.model.ShoppingCart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
//we use service interfaces to declare the methods we want done in the service implementations

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductToShoppingCart(String username, Long productId);

    List<ShoppingCart> findAll();


    ShoppingCart save(ShoppingCart shoppingCart);

    Optional<ShoppingCart> findById(Long id);
    List<ShoppingCart> filterByDateTimeBetween(LocalDateTime from, LocalDateTime to);


}
