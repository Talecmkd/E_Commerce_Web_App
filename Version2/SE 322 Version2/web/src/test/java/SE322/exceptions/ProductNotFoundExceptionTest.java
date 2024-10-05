package SE322.exceptions;

import SE322.model.exceptions.ProductNotFoundException;
import junit.framework.TestCase;
import org.junit.Test;

public class ProductNotFoundExceptionTest extends TestCase {

    private ProductNotFoundException exception;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the exception object to null before each test case.
        exception = null;
    }

    @Test
    public void testConstructorPositive() {
        // The testConstructorPositive method creates a ProductNotFoundException instance with a valid productId,
        // verifying that the message is initialized correctly.

        // Define the product ID that will be used to construct the exception
        Long productId = 123L;

        // Create the exception instance using the constructor
        exception = new ProductNotFoundException(productId);

        // Verify that the message is formatted correctly
        String expectedMessage = String.format("Product with id: %d was not found", productId);
        assertEquals("Exception message should match", expectedMessage, exception.getMessage());
    }

    @Test
    public void testConstructorNegative() {
        // The testConstructorNegative method creates a ProductNotFoundException instance with a null productId,
        // checking if the message is handled correctly. We assume that the constructor should handle nulls gracefully.

        // Define a null product ID
        Long productId = null;

        // Create the exception instance using the constructor
        exception = new ProductNotFoundException(productId);

        // Verify that the message is handled correctly
        String expectedMessage = "Product with id: null was not found";
        assertEquals("Exception message should handle null ID", expectedMessage, exception.getMessage());
    }
}
