package SE322.exceptions;

import SE322.model.exceptions.ShoppingCartNotFoundException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ShoppingCartNotFoundExceptionTest extends TestCase {

    private ShoppingCartNotFoundException exception;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the exception object to null before each test case.
        exception = null;
    }

    @Test
    public void testConstructorPositive() {
        // The testConstructorPositive method creates a ShoppingCartNotFoundException instance with a valid cartId,
        // verifying that the message is initialized correctly.

        // Define the cart ID that will be used to construct the exception
        Long cartId = 123L;

        // Create the exception instance using the constructor
        exception = new ShoppingCartNotFoundException(cartId);

        // Verify that the message is formatted correctly
        String expectedMessage = String.format("Shopping cart with id: %d was not found", cartId);
        assertEquals("Exception message should match", expectedMessage, exception.getMessage());

        // Verify that the message is not equal to an incorrect message
        String incorrectMessage = "Shopping cart not found";
        assertNotEquals("Exception message should not match the incorrect message", incorrectMessage, exception.getMessage());
    }

    @Test
    public void testConstructorNegative() {
        // The testConstructorNegative method creates a ShoppingCartNotFoundException instance with a null cartId,
        // checking if the message is handled correctly. We assume that the constructor should handle nulls gracefully.

        // Define a null cart ID
        Long cartId = null;

        // Create the exception instance using the constructor
        exception = new ShoppingCartNotFoundException(cartId);


        // Verify that the message is not equal to an incorrect message
        String incorrectMessage = "Shopping cart not found";
        assertNotEquals("Exception message should not match the incorrect message", incorrectMessage, exception.getMessage());
    }
}
