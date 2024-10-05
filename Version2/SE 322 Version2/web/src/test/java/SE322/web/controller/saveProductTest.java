package SE322.web.controller;

import SE322.service.ProductService;
import SE322.web.controller.ProductController;
import junit.framework.TestCase;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class saveProductTest extends TestCase {
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

    public void testSaveProduct_Success() {

        // Calling the method under test
        String viewName = productController.saveProduct("Test Product", 100.0, 5, 1L, 1L);


        // Asserting the view name
        assertEquals("View name should be /products","redirect:/products",viewName);
    }


    public void testSaveProductNegative() {
        // Call the method under test
        String viewName = productController.saveProduct("Test Product", 50.0, 5, 1L, 1L);

        assertNotEquals("invalid-template", viewName, "View name should not be  invalid-template");

    }
}
