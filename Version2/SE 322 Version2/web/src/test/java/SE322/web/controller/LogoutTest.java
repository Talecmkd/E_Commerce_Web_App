package SE322.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mockito.Mockito;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LogoutTest extends TestCase {

    private LogoutController logoutController;
    private HttpServletRequest request;
    private HttpSession session;
    private Model model;

    @Before
    public void setUp() throws Exception {
        logoutController = new LogoutController();
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class); // Create a mocked session
        Mockito.when(request.getSession()).thenReturn(session); // Return this mocked session
        model = new ConcurrentModel(); // Model to pass into controller methods
    }

    @Test
    public void testLogoutPositive() {
        String result = logoutController.logout(request, model);

        // Verify the session was invalidated
        Mockito.verify(session, Mockito.times(1)).invalidate(); // Ensure session is invalidated once

        // Check the expected redirection
        assertEquals("redirect:/login", result);
    }

    @Test
    public void testLogoutNegative() {
        String result = logoutController.logout(request, model);

        // Verify the session was invalidated
        Mockito.verify(session, Mockito.times(1)).invalidate(); // Ensure session is invalidated once

        // Check the expected redirection
        assertNotEquals("redirect:/invalid-template", result);
    }
}

