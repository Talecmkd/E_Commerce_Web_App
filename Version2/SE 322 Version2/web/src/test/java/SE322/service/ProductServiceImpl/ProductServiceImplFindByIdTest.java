package SE322.service.ProductServiceImpl;
import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
import SE322.repository.jpa.CategoryRepository;
import SE322.repository.jpa.ManufacturerRepository;
import SE322.repository.jpa.ProductRepository;
import SE322.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplFindByIdTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {


        Long productId = 1L;
        Product product = new Product("Test Product", 10.0, 5, new Category(), new Manufacturer());
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));


        Optional<Product> result = productService.findById(productId);

        assertEquals(product, result.orElseThrow()); // Ensure the product returned matches the one we expect
    }


    @Test
    void testFindByIdNegative() {
        Long productId = 1L;
        Product notExpectedProduct = new Product("Test Product", 10.0, 5, new Category(), new Manufacturer());
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> result = productService.findById(productId);

        assertFalse(result.isPresent()); // Ensure no product is returned
        assertNotEquals(notExpectedProduct, result.orElse(null)); // Ensure the returned product is not the unexpected one
    }

}
