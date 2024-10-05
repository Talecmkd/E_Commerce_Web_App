package SE322.web.controller;


import junit.framework.TestCase;
import SE322.model.Product;
import SE322.service.ProductService;
import SE322.web.controller.ProductController;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class getProductPageTest extends TestCase {

    ProductService productService;
    Model model;
    ProductController productController;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Creating mock ProductService, Model, and ProductController
        productService=mock(ProductService.class);
        model=mock(Model.class);
        productController=new ProductController(productService,null,null);

    }


    public void testGetProductPage() {
        // Preparing test data
        List<Product> products = new ArrayList<>();
        products.add(new Product("Test Product 1", 60d,10,null, null));
        products.add(new Product("Test Product 2", 75.5,12,null, null));


        // Calling the method under test
        String viewName = productController.getProductPage(null, model);

        // Asserting the view name
        assertEquals("View name should be master-template","master-template",viewName);
    }
    public void testGetProductPageNegative() {
        // Preparing test data
        List<Product> products = new ArrayList<>();
        products.add(new Product("Test Product 1", 60d,10,null, null));
        products.add(new Product("Test Product 2", 75.5,12,null, null));

        // Calling the method under test
        String viewName = productController.getProductPage(null, model);

        // Asserting the view name is not equal to "invalid-template"
        assertNotEquals("invalid-template", viewName,"View name should not be invalid-template");
    }
}
