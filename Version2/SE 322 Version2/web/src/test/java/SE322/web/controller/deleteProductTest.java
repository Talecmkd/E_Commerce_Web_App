package SE322.web.controller;

import SE322.service.ProductService;
import SE322.web.controller.ProductController;
import junit.framework.TestCase;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class deleteProductTest extends TestCase {
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

    public void testDeleteProduct() {

        // Calling the method under test
        String viewName = productController.deleteProduct(123L);


        // Asserting the view name
        assertEquals("View name should be 'redirect:/products' after deletion","redirect:/products", viewName );
    }

    public void testDeleteProductNegative() {
        // Calling the method under test
        String viewName = productController.deleteProduct(123L);

        // Asserting the view name is not equal to "redirect:/products" after deletion
        assertNotEquals( "redirect:/products/{id}", viewName,"View name should not be 'redirect:/products/{id} after deletion");
    }
}
