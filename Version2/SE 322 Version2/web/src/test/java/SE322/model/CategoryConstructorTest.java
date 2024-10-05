package SE322.model;

import junit.framework.TestCase;
import org.junit.Test;


public class CategoryConstructorTest extends TestCase {
    private Category category;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the category object to null before each test case.
        category = null;
    }

    @Test
    public void testConstructorPositive() {
        // The testConstructorPositive method creates a Category instance with valid name and description,
        // verifying that the fields are initialized correctly.

        // Create a category instance using the constructor
        String name = "Test Category";
        String description = "This is a test category description.";
        category = new Category(name, description);

        // Verify that the fields are initialized correctly
        assertNull("ID should be null", category.getId());
        assertEquals("Name should match", name, category.getName());
        assertEquals("Description should match", description, category.getDescription());
    }

    @Test
    public void testConstructorNegative() {
        // The testConstructorNegative method creates a Category instance with null name and description,
        // checking if the fields are initialized to null.

        // Create a category instance with null name and description
        category = new Category(null, null);

        // Verify that the fields are initialized correctly
        assertNull("ID should be null", category.getId());
        assertNull("Name should be null", category.getName());
        assertNull("Description should be null", category.getDescription());
    }
}