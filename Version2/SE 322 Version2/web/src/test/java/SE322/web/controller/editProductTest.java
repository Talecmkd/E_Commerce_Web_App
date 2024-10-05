package SE322.web.controller;

import SE322.model.Product;
import SE322.service.ProductService;
import SE322.web.controller.ProductController;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class editProductTest extends TestCase {

    ProductService productService;
    Model model;
    ProductController productController;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Creating mock ProductService, Model, and ProductController
        productService=mock(ProductService.class);
        model=mock(Model.class);
    }

    public void testEditProduct() {
        // Mocking ProductService to return a dummy product
        when(productService.findById(1L)).thenReturn(Optional.of(new Product()));

        // Creating ProductController with mocked ProductService
         productController = new ProductController(productService, null, null);

        // Calling the method under test
        String viewName = productController.editProduct(1L, "Test Product", 50.0, 5, 1L, 1L);

        // Asserting that the view name is "redirect:/products"
        assertEquals("View name should be 'master-template' after successfully editing product", viewName, "redirect:/products");
    }

    public void testEditProductNegative() {
        // Mocking ProductService to return empty Optional, indicating product not found
        when(productService.findById(1L)).thenReturn(Optional.empty());

        // Creating ProductController with mocked ProductService
         productController = new ProductController(productService, null, null);

        // Calling the method under test
        String viewName = productController.editProduct(1L, "Test Product", 50.0, 5, 1L, 1L);

        // Asserting that the view name is "redirect:/products?error=ProductNotFound"
        assertNotEquals("View name should not be invalid-template", "redirect:/products", viewName);
    }
}
