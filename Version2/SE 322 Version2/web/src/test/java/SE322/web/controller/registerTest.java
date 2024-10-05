package SE322.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import SE322.service.AuthService;
import SE322.service.UserService;
import SE322.model.Role;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class registerTest {

    @Mock
    private AuthService mockAuthService;

    @Mock
    private UserService mockUserService;

    @InjectMocks
    private RegisterController registerController;

    @BeforeEach
    public void setUp() {
        // Initialize mocks and inject them into the controller
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterPositive() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        String repeatedPassword = "testPassword";
        String name = "John";
        String surname = "Doe";
        Role role = Role.ROLE_USER;

        // Act
        String result = registerController.register(username, password, repeatedPassword, name, surname, role);

        // Assert
        assertEquals("redirect:/login", result);
        // Additional assertions can be made to verify the state of the system after registration
    }

    @Test
    public void testRegisterNegative() {
        // Arrange
        String username = ""; // Empty username
        String password = "testPassword";
        String repeatedPassword = "testPassword";
        String name = "John";
        String surname = "Doe";
        Role role = Role.ROLE_USER;

        // Act
        String result = registerController.register(username, password, repeatedPassword, name, surname, role);

        // Assert
        assertNotEquals("redirect:/register?error=Invalid%20username.", result);
    }
}