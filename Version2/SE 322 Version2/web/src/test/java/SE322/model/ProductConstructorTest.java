package SE322.model;

import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
import SE322.service.ProductService;
import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class ProductConstructorTest extends TestCase {
    Category category;
    Manufacturer manufacturer;
    Product product;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
         category = new Category("Electronics","elec");
         manufacturer = new Manufacturer("El Comp.","mach");
         product = new Product("Laptop", 999.99, 10, category, manufacturer);
    }

    public void testProductConstructor() {

        assertEquals("Laptop", product.getName());
        assertEquals(999.99, product.getPrice());
        assertEquals(10,(int)product.getQuantity());
        assertEquals(category, product.getCategory());
        assertEquals(manufacturer, product.getManufacturer());
    }

    public void testProductConstructorNegative(){
        Category category1= new Category("smth","smth");
        Manufacturer manufacturer1=new Manufacturer("msmth","msmth");
        assertNotEquals("Laptop1", product.getName());
        assertNotEquals(1000d, product.getPrice());
        assertNotEquals(11,(int)product.getQuantity());
        assertNotEquals(category1, product.getCategory());
        assertNotEquals(manufacturer1, product.getManufacturer());
    }
}

