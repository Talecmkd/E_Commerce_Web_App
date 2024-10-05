package SE322.bootstrap;

import SE322.model.*;
import SE322.model.embeddables.UserAddress;
import SE322.repository.jpa.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<User> users = null;
    public static List<Category> categories = null;
    public static List<Manufacturer> manufacturers = null;
    public static List<Product> products = null;
    public static List<ShoppingCart> shoppingCarts = null;


    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository, ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        categories = new ArrayList<>();
        manufacturers = new ArrayList<>();
        products = new ArrayList<>();
        shoppingCarts = new ArrayList<>();

        // We are adding Users into the list with their specified roles which we will use for authentication later
        if (userRepository.count() == 0) {
            users.add(
                    new User(
                            "user",
                            passwordEncoder.encode("user"),
                            "user",
                            "user",
                            new UserAddress("Turkey", "Izmir", "St. street123", "1/2"),
                            Role.ROLE_USER
            ));

            users.add(
                    new User(
                            "admin",
                            passwordEncoder.encode("admin"),
                            "admin",
                            "admin",
                            new UserAddress(),
                            Role.ROLE_ADMIN
                    )
            );
            userRepository.saveAll(users); // With this line they are saved in the memory and will be available
                                            // to use once we run the app
        }

        if (categoryRepository.count() == 0) {
            categories.add(new Category("Books", "Books category"));
            categories.add(new Category("Sports", "Sports category"));
            categories.add(new Category("Food", "Food category"));
            categoryRepository.saveAll(categories); // same thing for here
        }

        if (manufacturerRepository.count() == 0) {
            manufacturers.add(new Manufacturer("Nike", "USA"));
            manufacturers.add(new Manufacturer("Coca Cola", "USA"));
            manufacturers.add(new Manufacturer("Literature", "MK"));
            manufacturerRepository.saveAll(manufacturers); // and here
        }

        if (productRepository.count() == 0) {
            List<Category> categoryList = categoryRepository.findAll();
            List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
            // We are adding products into the list while getting the correct category and manufacturer from their lists
            products.add(new Product("Shoes", 30d, 1000, categoryList.get(0), manufacturerList.get(0)));
            products.add(new Product("Hoodie", 50.99, 200, categoryList.get(0), manufacturerList.get(1)));
            products.add(new Product("Gloves", 10d, 56000, categoryList.get(1), manufacturerList.get(2)));
            products.add(new Product("Guitar", 590.99, 20, categoryList.get(2), manufacturerList.get(2)));
            productRepository.saveAll(products); // right here we are saving the products into the memory and
                                                    // they will be available to see once we start the app
        }

    }
}
