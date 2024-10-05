package SE322.web.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

public class GetHomePageTest{

    private HomeController homeController;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        // Initialize HomeController and inject the mock Model
        MockitoAnnotations.initMocks(this);
        homeController = new HomeController();
    }

    @Test
    public void testGetHomePagePositive() {
        // Mock behavior of Model
        when(model.addAttribute("bodyContent", "home")).thenReturn(model);

        // Call the getHomePage method
        String result = homeController.getHomePage(model);

        // Assert that the returned view name is "master-template"
        assertEquals("master-template", result);
    }

    @Test
    public void testGetHomePageNegative() {
        // Mock behavior of Model (no need to configure for this test)

        // Call the getHomePage method
        String result = homeController.getHomePage(model);

        // Assert that the returned view name is not "invalid-template"
        assertNotEquals("invalid-template", result);
    }
}
