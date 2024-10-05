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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplFindAllTest {

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
    void testFindAll() {
        //Making a product list
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product1", 10.0, 5, new Category(), new Manufacturer()));
        productList.add(new Product("Product2", 15.0, 3, new Category(), new Manufacturer()));
        when(productRepository.findAll()).thenReturn(productList);

        //getting the result of the method findAll
        List<Product> result = productService.findAll();

        //seeing if the result is equal
        assertEquals(2, result.size());
    }
    @Test
    void testFindAllNegative() {
        //Making a product list
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product1", 10.0, 5, new Category(), new Manufacturer()));
        productList.add(new Product("Product2", 15.0, 3, new Category(), new Manufacturer()));
        when(productRepository.findAll()).thenReturn(productList);

        //getting the result of the method findAll
        List<Product> result = productService.findAll();

        //seeing if the result is equal
        assertNotEquals(3, result.size());
    }
}
