package SE322.web.controller;

import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
import SE322.service.CategoryService;
import SE322.service.ManufacturerService;
import SE322.service.ProductService;
import SE322.web.controller.ProductController;
import junit.framework.TestCase;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class editProductPageTest extends TestCase {
    ProductService productService;
    CategoryService categoryService;
    ManufacturerService manufacturerService;
    Model model;
    ProductController productController;
    Product product;
    Manufacturer manufacturer;
    Category category;
    long productId=1L;

    @Override
    protected void setUp() throws Exception {
        // Creating mock ProductService, Model, and ProductController
        super.setUp();
        productService = mock(ProductService.class);
        categoryService = mock(CategoryService.class);
        manufacturerService = mock(ManufacturerService.class);
        model = mock(Model.class);
        productController = new ProductController(productService, categoryService, manufacturerService);
        product = new Product("Test Product", 100.0, 5, null, null);
        manufacturer = new Manufacturer("Manufacturer","Manadd");
        category = new Category("Category","cat1");

    }

    public void testEditProductPage() {

        // Preparing test data
        when(productService.findById(productId)).thenReturn(Optional.of(product));
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(manufacturer);
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        // Calling the method under test
        String viewName = productController.editProductPage(productId, model);

        // Asserting the view name
        assertEquals("View name should be 'master-template'","master-template", viewName);

    }

    public void testEditProductPageNegative() {

        // Preparing test data
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(manufacturer);
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        // Calling the method under test
        String viewName = productController.editProductPage(productId, model);

        // Assert the product name is not equal to "Another Product"
        assertNotEquals("invalid-template", viewName,"View name should not be invalid-template ");
    }
}
