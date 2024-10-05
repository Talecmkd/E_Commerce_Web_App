package SE322.service.ProductServiceImpl;
import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
import SE322.model.exceptions.ProductNotFoundException;
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

public class ProductServiceImplEditTest {

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
    void testEdit() {
        Long productId = 1L;
        String newName = "New Product Name";
        Double newPrice = 20.0;
        Integer newQuantity = 8;
        Long categoryId = 1L;
        Long manufacturerId = 1L;
        Product existingProduct = new Product("Old Product Name", 10.0, 5, new Category(), new Manufacturer());
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        Category newCategory = new Category();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(newCategory));
        Manufacturer newManufacturer = new Manufacturer();
        when(manufacturerRepository.findById(manufacturerId)).thenReturn(Optional.of(newManufacturer));

        Optional<Product> result = productService.edit(productId, newName, newPrice, newQuantity, categoryId, manufacturerId);

        assertTrue(result.isPresent());
        assertEquals(newName, result.get().getName());
        assertEquals(newPrice, result.get().getPrice());
        assertEquals(newQuantity, result.get().getQuantity());
        assertEquals(newCategory, result.get().getCategory());
        assertEquals(newManufacturer, result.get().getManufacturer());
    }


    @Test
    void testEditNegative() {
        Long productId = 1L;
        String newName = "New Product Name";
        Double newPrice = 20.0;
        Integer newQuantity = 8;
        Long categoryId = 1L;
        Long manufacturerId = 1L;
        Product existingProduct = new Product("Old Product Name", 10.0, 5, new Category(), new Manufacturer());
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        Category newCategory = new Category();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(newCategory));
        Manufacturer newManufacturer = new Manufacturer();
        when(manufacturerRepository.findById(manufacturerId)).thenReturn(Optional.of(newManufacturer));

        Optional<Product> result = productService.edit(productId, newName, newPrice, newQuantity, categoryId, manufacturerId);

        assertNotEquals("newName", result.get().getName());
        assertNotEquals("newPrice", result.get().getPrice());
        assertNotEquals("newQuantity", result.get().getQuantity());
        assertNotEquals("newCategory", result.get().getCategory());
        assertNotEquals("newManufacturer", result.get().getManufacturer());
    }


}
