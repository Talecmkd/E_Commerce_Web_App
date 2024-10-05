package SE322.service.impl;

import SE322.model.Product;
import SE322.model.ShoppingCart;
import SE322.model.exceptions.ProductAlreadyInShoppingCartException;
import SE322.model.exceptions.ProductNotFoundException;
import SE322.model.exceptions.ShoppingCartNotFoundException;
import SE322.model.exceptions.UserNotFoundException;
import SE322.service.ProductService;
import SE322.service.ShoppingCartService;
import SE322.model.User;
import SE322.model.enumerations.ShoppingCartStatus;
import SE322.repository.jpa.ShoppingCartRepository;
import SE322.repository.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    //we use repositories because we want to manipulate with the data in the actual databases
    //and as previously stated the JpaRepository already has a lot of existing methods, so we don't need to
    //write our own code so much
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        Optional<ShoppingCart> shoppingCartOptional = this.shoppingCartRepository.findById(cartId);

        if (shoppingCartOptional.isEmpty()) {
            throw new ShoppingCartNotFoundException(cartId);
        }

        return shoppingCartOptional.get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.shoppingCartRepository
                .findByUserUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userRepository.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username)); // throwing an error if the username doesn't exist
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        Product product = this.productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        List<Product> productsInShoppingCart = shoppingCart.getProducts().stream()
                .filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList());

        if (productsInShoppingCart.size() > 0) {
            throw new ProductAlreadyInShoppingCartException(productId, username);
        }

        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }



    @Override
    public List<ShoppingCart> findAll() {
        return this.shoppingCartRepository.findAll();
    }



    @Override
    public ShoppingCart save(ShoppingCart cart) {
        return this.shoppingCartRepository.save(cart);
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return this.shoppingCartRepository.findById(id);
    }

    @Override
    public List<ShoppingCart> filterByDateTimeBetween(LocalDateTime from, LocalDateTime to) {
        return this.shoppingCartRepository.findByDateCreatedBetween(from, to);
    }

}

