package SE322.exceptions;

import SE322.model.exceptions.InvalidUserCredentialsException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class InvalidUserCredentialsExceptionTest extends TestCase {

    private InvalidUserCredentialsException exception;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the exception object to null before each test case.
        exception = null;
    }

    @Test
    public void testConstructorMessageEquals() {
        // The testConstructorMessageEquals method creates an InvalidUserCredentialsException instance,
        // verifying that the message is initialized correctly.

        // Create the exception instance using the constructor
        exception = new InvalidUserCredentialsException();

        // Verify that the message is formatted correctly
        String expectedMessage = "Invalid user credentials";
        assertEquals("Exception message should match", expectedMessage, exception.getMessage());
    }

    @Test
    public void testConstructorMessageNotEquals() {
        // The testConstructorMessageNotEquals method creates an InvalidUserCredentialsException instance,
        // verifying that the message does not match an incorrect message.

        // Create the exception instance using the constructor
        exception = new InvalidUserCredentialsException();

        // Verify that the message is not equal to an incorrect message
        String incorrectMessage = "User credentials are invalid";
        assertNotEquals("Exception message should not match the incorrect message", incorrectMessage, exception.getMessage());
    }
}
