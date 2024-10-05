package SE322.exceptions;

import SE322.model.exceptions.UserNotFoundException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserNotFoundExceptionTest extends TestCase {

    private UserNotFoundException exception;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the exception object to null before each test case.
        exception = null;
    }

    @Test
    public void testConstructorMessageEquals() {
        // The testConstructorMessageEquals method creates a UserNotFoundException instance,
        // verifying that the message is initialized correctly.

        // Define the username that will be used to construct the exception
        String username = "testUser";

        // Create the exception instance using the constructor
        exception = new UserNotFoundException(username);

        // Verify that the message is formatted correctly
        String expectedMessage = String.format("User with username: %s was not found", username);
        assertEquals("Exception message should match", expectedMessage, exception.getMessage());
    }

    @Test
    public void testConstructorMessageNotEquals() {
        // The testConstructorMessageNotEquals method creates a UserNotFoundException instance,
        // verifying that the message does not match an incorrect message.

        // Define the username that will be used to construct the exception
        String username = "testUser";

        // Create the exception instance using the constructor
        exception = new UserNotFoundException(username);

        // Verify that the message is not equal to an incorrect message
        String incorrectMessage = "User not found.";
        assertNotEquals("Exception message should not match the incorrect message", incorrectMessage, exception.getMessage());
    }
}
