package SE322.exceptions;

import SE322.model.exceptions.InvalidArgumentsException;
import junit.framework.TestCase;
import org.junit.Test;

public class InvalidArgumentsExceptionTest extends TestCase {

    private InvalidArgumentsException exception;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the exception object to null before each test case.
        exception = null;
    }

    @Test
    public void testConstructorPositive() {
        // The testConstructorPositive method creates an InvalidArgumentsException instance,
        // verifying that the message is initialized correctly.

        // Create the exception instance using the constructor
        exception = new InvalidArgumentsException();

        // Verify that the message is formatted correctly
        String expectedMessage = "Invalid argument.";
        assertEquals("Exception message should match", expectedMessage, exception.getMessage());
    }

    @Test
    public void testConstructorNegative() {
        // The testConstructorNegative method checks the behavior of InvalidArgumentsException
        // when the message is not the expected one, which is not quite applicable here,
        // but we can test the exception instance for being non-null.

        // Create the exception instance using the constructor
        exception = new InvalidArgumentsException();

        // Verify that the exception is not null
        assertNotNull("Exception instance should not be null", exception);
    }
}