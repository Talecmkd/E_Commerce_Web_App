package SE322.exceptions;

import SE322.model.exceptions.UsernameAlreadyExistsException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UsernameAlreadyExistsExceptionTest extends TestCase {

    private UsernameAlreadyExistsException exception;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the exception object to null before each test case.
        exception = null;
    }

    @Test
    public void testConstructorMessageEquals() {
        // The testConstructorMessageEquals method creates a UsernameAlreadyExistsException instance,
        // verifying that the message is initialized correctly.

        // Define the username that will be used to construct the exception
        String username = "testUser";

        // Create the exception instance using the constructor
        exception = new UsernameAlreadyExistsException(username);

        // Verify that the message is formatted correctly
        String expectedMessage = String.format("User with username: %s already exists", username);
        assertEquals("Exception message should match", expectedMessage, exception.getMessage());
    }

    @Test
    public void testConstructorMessageNotEquals() {
        // The testConstructorMessageNotEquals method creates a UsernameAlreadyExistsException instance,
        // verifying that the message does not match an incorrect message.

        // Define the username that will be used to construct the exception
        String username = "testUser";

        // Create the exception instance using the constructor
        exception = new UsernameAlreadyExistsException(username);

        // Verify that the message is not equal to an incorrect message
        String incorrectMessage = "Username already exists.";
        assertNotEquals("Exception message should not match the incorrect message", incorrectMessage, exception.getMessage());
    }
}
