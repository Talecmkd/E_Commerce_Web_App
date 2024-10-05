package SE322.web.controller;

import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GetLoginPageTest extends TestCase {
    private LoginController loginController;
    private Model model;

    @Before
    public void setUp() throws Exception {
        loginController = new LoginController(null); // AuthService isn't used in getLoginPage, so we can pass null
        model = new ConcurrentModel(); // Model for storing attributes
    }

    @Test
    public void testGetLoginPagePositive() {
        // Call the getLoginPage method
        String viewName = loginController.getLoginPage(model);

        // Verify the expected view name is returned
        assertEquals("master-template", viewName);

        // Check if the expected model attribute is set
        assertTrue(model.containsAttribute("bodyContent"));
        assertEquals("login", model.asMap().get("bodyContent"));
    }

    @Test
    public void testGetLoginPageNegative() {
        // Check that the returned view name is not incorrect
        String viewName = loginController.getLoginPage(model);

        // Make sure it does not return an unexpected view name
        assertNotEquals("wrong-template", viewName);

        // Ensure that model's 'bodyContent' is not set to something unexpected
        assertNotEquals("error", model.asMap().get("bodyContent"));

        // Additional checks
        assertNotNull(viewName); // Make sure the view name is not null
        assertNotNull(model.asMap().get("bodyContent")); // Ensure 'bodyContent' is set

        // The expected positive result should still hold
        assertEquals("master-template", viewName);
        assertEquals("login", model.asMap().get("bodyContent"));
    }
}
