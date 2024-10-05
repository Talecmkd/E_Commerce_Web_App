package SE322.web.controller;

import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.service.CategoryService;
import SE322.service.ManufacturerService;
import SE322.service.ProductService;
import SE322.web.controller.ProductController;
import junit.framework.TestCase;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class addProductPageTest extends TestCase {
    ProductService productService;
    CategoryService categoryService;
    ManufacturerService manufacturerService;
    Model model;
    ProductController productController;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Creating mock ProductService, Model, and ProductController
        ProductService productService = mock(ProductService.class);
        CategoryService categoryService = mock(CategoryService.class);
        ManufacturerService manufacturerService = mock(ManufacturerService.class);
         model = mock(Model.class);
         productController = new ProductController(productService, categoryService, manufacturerService);

    }

    public void testAddProductPage() {

        // Preparing test data
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer("Manufacturer 1","man1"));
        manufacturers.add(new Manufacturer("Manufacturer 2","man2"));
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Category 1","cat1"));
        categories.add(new Category("Category 2","cat2"));

        // Call the method under test
        String viewName = productController.addProductPage(model);


        // Assert the view name
        assertEquals("View name should be 'master-template", "master-template", viewName);
    }
    public void testAddProductPageNegative() {

        // Preparing test data
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer("Manufacturer 1","man1"));
        manufacturers.add(new Manufacturer("Manufacturer 2","man2"));
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Category 1","cat1"));
        categories.add(new Category("Category 2","cat2"));

        // Call the method under test
        String viewName = productController.addProductPage(model);


        // Assert the view name
        assertNotEquals( "invalid-template", viewName,"View name should not be  invalid-template");
    }
}
