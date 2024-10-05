package SE322.service.ProductServiceImpl;

import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
import SE322.model.exceptions.ProductNotFoundException;
import SE322.repository.jpa.CategoryRepository;
import SE322.repository.jpa.ManufacturerRepository;
import SE322.repository.jpa.ProductRepository;
import SE322.service.impl.ProductServiceImpl;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplDeleteByIdTest extends TestCase {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteById() {
        Long productId = 1L;
        Product existingProduct = new Product("Old Product Name", 10.0, 5, new Category(), new Manufacturer());
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        productService.deleteById(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testDeleteByIdNegative() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> deletedProduct = productService.findById(productId);

        assertFalse(deletedProduct.isPresent());
        assertNotEquals(productId, deletedProduct.map(Product::getId).orElse(null));
    }


}
