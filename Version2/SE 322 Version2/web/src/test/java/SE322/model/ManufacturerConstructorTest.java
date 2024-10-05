package SE322.model;

import junit.framework.TestCase;
import org.junit.Test;

public class ManufacturerConstructorTest extends TestCase {
    private Manufacturer manufacturer;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        // The setUp method initializes the manufacturer object to null before each test case.
        manufacturer = null;
    }

    @Test
    public void testConstructorPositive() {
        // The testConstructorPositive method creates a Manufacturer instance with valid name and address,
        // verifying that the fields are initialized correctly.

        // Create a manufacturer instance using the constructor
        String name = "Test Manufacturer";
        String address = "123 Test Address";
        manufacturer = new Manufacturer(name, address);

        // Verify that the fields are initialized correctly
        assertNull("ID should be null", manufacturer.getId());
        assertEquals("Name should match", name, manufacturer.getName());
        assertEquals("Address should match", address, manufacturer.getAddress());
    }

    @Test
    public void testConstructorNegative() {
        // The testConstructorNegative method creates a Manufacturer instance with null name and address,
        // checking if the fields are initialized to null.

        // Create a manufacturer instance with null name and address
        manufacturer = new Manufacturer(null, null);

        // Verify that the fields are initialized correctly
        assertNull("ID should be null", manufacturer.getId());
        assertNull("Name should be null", manufacturer.getName());
        assertNull("Address should be null", manufacturer.getAddress());
    }
}