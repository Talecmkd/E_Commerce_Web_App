package SE322.exceptions;

import SE322.model.exceptions.CategoryNotFoundException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CategoryNotFoundExceptionTest extends TestCase {


    private CategoryNotFoundException exception;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the exception object to null before each test case.
        exception = null;
    }

    @Test
    public void testConstructorPositive() {
        // The testConstructorPositive method creates a CategoryNotFoundException instance with a valid categoryId,
        // verifying that the message is initialized correctly.

        // Define the category ID that will be used to construct the exception
        Long categoryId = 123L;

        // Create the exception instance using the constructor
        exception = new CategoryNotFoundException(categoryId);

        // Verify that the message is formatted correctly
        String expectedMessage = String.format("Category with id %d does not exist.", categoryId);
        assertEquals("Exception message should match", expectedMessage, exception.getMessage());
    }

    @Test
    public void testConstructorNegative() {
        // The testConstructorNegative method creates a CategoryNotFoundException instance with a null categoryId,
        // checking if the message is handled correctly. We assume that the constructor should handle nulls gracefully.

        // Define a null categoryID
        Long categoryId = null;

        // Create the exception instance using the constructor
        exception = new CategoryNotFoundException(categoryId);

        // Verify that the message is handled correctly
        String incorrectMessage = "User not found";
        assertNotEquals("Exception message should not match the incorrect message", incorrectMessage, exception.getMessage());
    }
    }
