package SE322.exceptions;

import SE322.model.exceptions.ProductAlreadyInShoppingCartException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductAlreadyInShoppingCartExceptionTest extends TestCase {

    private ProductAlreadyInShoppingCartException exception;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the exception object to null before each test case.
        exception = null;
    }

    @Test
    public void testConstructorMessageEquals() {
        // The testConstructorMessageEquals method creates a ProductAlreadyInShoppingCartException instance,
        // verifying that the message is initialized correctly.

        // Define the product ID and username that will be used to construct the exception
        Long productId = 123L;
        String username = "testUser";

        // Create the exception instance using the constructor
        exception = new ProductAlreadyInShoppingCartException(productId, username);

        // Verify that the message is formatted correctly
        String expectedMessage = String.format("Product with id: %d already exists in shopping cart for user with username %s", productId, username);
        assertEquals("Exception message should match", expectedMessage, exception.getMessage());
    }

    @Test
    public void testConstructorMessageNotEquals() {
        // The testConstructorMessageNotEquals method creates a ProductAlreadyInShoppingCartException instance,
        // verifying that the message does not match an incorrect message.

        // Define the product ID and username that will be used to construct the exception
        Long productId = 123L;
        String username = "testUser";

        // Create the exception instance using the constructor
        exception = new ProductAlreadyInShoppingCartException(productId, username);

        // Verify that the message is not equal to an incorrect message
        String incorrectMessage = "Product is already in the shopping cart.";
        assertNotEquals("Exception message should not match the incorrect message", incorrectMessage, exception.getMessage());
    }
}
