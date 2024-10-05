package SE322.service.ProductServiceImpl;
import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
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

public class ProductServiceImplFindByNameTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByName_Success() {
        String productName = "Test Product";
        Product product = new Product(productName, 10.0, 5, new Category(), new Manufacturer());
        when(productRepository.findByName(productName)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.findByName(productName);

        assertEquals(product, result.orElseThrow()); // Ensure the correct product is returned
    }

    @Test
    void testFindByName_ProductNotFound() {
        String productName = "Nonexistent Product";
        when(productRepository.findByName(productName)).thenReturn(Optional.empty());

    }
}
