package SE322.model;

import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductDataUpdateTest extends TestCase {
    Category category;
    Manufacturer manufacturer;
    Product product;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
         category = new Category("el","el");
         manufacturer = new Manufacturer("elc","elc");
         product = new Product("Headphones", 49.99, 50, category, manufacturer);
    }

    public void testUpdateProductData() {
        Category category1 = new Category("el1","el1");
        Manufacturer manufacturer1 = new Manufacturer("el1c","el1c");

        product.setName("Headphones1");
        product.setPrice(59.99);
        product.setQuantity(60);
        product.setCategory(category1);
        product.setManufacturer(manufacturer1);

        assertEquals("Headphones1",product.getName());
        assertEquals(59.99, product.getPrice());
        assertEquals(60, (int)product.getQuantity());
        assertEquals(category1,product.getCategory());
        assertEquals(manufacturer1,product.getManufacturer());

    }
    public void testUpdateProductDataNegative() {
        Category category1 = new Category("el1","el1");
        Manufacturer manufacturer1 = new Manufacturer("el1c","el1c");

        product.setName("Headphones1");
        product.setPrice(59.99);
        product.setQuantity(60);
        product.setCategory(category1);
        product.setManufacturer(manufacturer1);

        assertNotEquals("Headphones",product.getName());
        assertNotEquals(57.7, product.getPrice());
        assertNotEquals(61, (int)product.getQuantity());
        assertNotEquals(category,product.getCategory());
        assertNotEquals(manufacturer,product.getManufacturer());

    }
}
