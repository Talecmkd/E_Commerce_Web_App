package SE322.exceptions;

import SE322.model.exceptions.PasswordsDoNotMatchException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordsDoNotMatchExceptionTest extends TestCase {

    private PasswordsDoNotMatchException exception;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the exception object to null before each test case.
        exception = null;
    }

    @Test
    public void testConstructorMessageEquals() {
        // The testConstructorMessageEquals method creates a PasswordsDoNotMatchException instance,
        // verifying that the message is initialized correctly.

        // Create the exception instance using the constructor
        exception = new PasswordsDoNotMatchException();

        // Verify that the message is formatted correctly
        String expectedMessage = "The Password and Repeat password fields do not match.";
        assertEquals("Exception message should match", expectedMessage, exception.getMessage());
    }

    @Test
    public void testConstructorMessageNotEquals() {
        // The testConstructorMessageNotEquals method creates a PasswordsDoNotMatchException instance,
        // verifying that the message does not match an incorrect message.

        // Create the exception instance using the constructor
        exception = new PasswordsDoNotMatchException();

        // Verify that the message is not equal to an incorrect message
        String incorrectMessage = "Passwords do not match.";
        assertNotEquals("Exception message should not match the incorrect message", incorrectMessage, exception.getMessage());
    }
}
