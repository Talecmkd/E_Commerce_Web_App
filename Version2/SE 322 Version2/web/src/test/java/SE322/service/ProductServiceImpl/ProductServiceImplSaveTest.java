package SE322.service.ProductServiceImpl;

import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
import SE322.model.exceptions.CategoryNotFoundException;
import SE322.model.exceptions.ManufacturerNotFoundException;
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

public class ProductServiceImplSaveTest {

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
    void testSave_Success() {
        String productName = "Test Product";
        Double productPrice = 10.0;
        Integer productQuantity = 5;
        Long categoryId = 1L;
        Long manufacturerId = 1L;
        Category category = new Category();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        Manufacturer manufacturer = new Manufacturer();
        when(manufacturerRepository.findById(manufacturerId)).thenReturn(Optional.of(manufacturer));
        Product savedProduct = new Product(productName, productPrice, productQuantity, category, manufacturer);
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Optional<Product> result = productService.save(productName, productPrice, productQuantity, categoryId, manufacturerId);

        assertTrue(result.isPresent());
        assertEquals(savedProduct, result.get());
    }

    @Test
    void testSave_CategoryNotFound() {
        String productName = "Test Product";
        Double productPrice = 10.0;
        Integer productQuantity = 5;
        Long categoryId = 1L;
        Long manufacturerId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        CategoryNotFoundException exception = null;
        try {
            productService.save(productName, productPrice, productQuantity, categoryId, manufacturerId).orElseThrow();
        } catch (CategoryNotFoundException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertNotEquals(null, exception.getMessage());
    }

    @Test
    void testSave_ManufacturerNotFound() {
        String productName = "Test Product";
        Double productPrice = 10.0;
        Integer productQuantity = 5;
        Long categoryId = 1L;
        Long manufacturerId = 1L;
        Category category = new Category();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(manufacturerRepository.findById(manufacturerId)).thenReturn(Optional.empty());

        // When
        ManufacturerNotFoundException exception = null;
        try {
            productService.save(productName, productPrice, productQuantity, categoryId, manufacturerId).orElseThrow();
        } catch (ManufacturerNotFoundException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertNotEquals(null, exception.getMessage());
    }
}
