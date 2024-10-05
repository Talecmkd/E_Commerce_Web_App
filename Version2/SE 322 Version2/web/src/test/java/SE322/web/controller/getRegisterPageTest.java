package SE322.web.controller;

import junit.framework.TestCase;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class getRegisterPageTest extends TestCase {

    private RegisterController registerController;
    private Model model;

    @Override
    public void setUp() {
        // Initialize RegisterController and mock Model before each test
        model = mock(Model.class);
        registerController = new RegisterController(null, null);
    }

    public void testGetRegisterPagePositive() {
        // Call the getRegisterPage method without error
        String result = registerController.getRegisterPage(null, model);

        // Verify the expected view name
        assertEquals("master-template", result);
    }

    public void testGetRegisterPageNegative() {
        // Error message
        String error = "InvalidArgumentsException";

        // Call the getRegisterPage method with error
        String result = registerController.getRegisterPage(error, model);

        // Verify the expected view name
        assertNotEquals("invalid-template", result);
    }
}

