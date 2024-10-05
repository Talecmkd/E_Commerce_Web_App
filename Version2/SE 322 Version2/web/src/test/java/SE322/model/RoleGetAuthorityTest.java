package SE322.model;

import junit.framework.TestCase;
import org.junit.Test;

public class RoleGetAuthorityTest extends TestCase {
    private Role role;

    @Override
    protected void setUp() {
        // Initialize common objects before each test case
        role = null;
    }

    @Test
    public void testGetAuthorityPositive() {
        // Set up the role for positive test case
        role = Role.ROLE_USER;

        // Call the method to get authority
        String authority = role.getAuthority();

        // Assert that the authority matches the name of the enum
        assertEquals("ROLE_USER", authority);
    }

    @Test
    public void testGetAuthorityNegative() {
        // Set up the role for negative test case
        role = Role.ROLE_USER;

        // Call the method to get authority
        String authority = role.getAuthority();

        // Assert that the authority does not match an incorrect value
        assertFalse("ROLE_ADMIN".equals(authority));
    }
}