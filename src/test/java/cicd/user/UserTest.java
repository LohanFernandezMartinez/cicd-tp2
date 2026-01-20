package cicd.user;

import com.devops.cicd.user.Role;
import com.devops.cicd.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final String VALID_PWD = "Password123!"; // Supposé fort selon PasswordPolicy

    @Test
    void shouldCreateValidUser() {
        User user = new User("alice@test.com", VALID_PWD, Role.USER);
        assertEquals("alice@test.com", user.getEmail());
        assertFalse(user.canAccessAdminArea());
    }

    @Test
    void shouldTrimEmail() {
        User user = new User("  bob@test.com  ", VALID_PWD, Role.USER);
        assertEquals("bob@test.com", user.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "alice", "alice@", "@test.com", "alice@test", "alice@@test.com"})
    void shouldThrowExceptionForInvalidEmail(String invalidEmail) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new User(invalidEmail, VALID_PWD, Role.USER));
        assertEquals("email must be valid", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRoleIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new User("test@test.com", VALID_PWD, null));
        assertEquals("role must not be null", exception.getMessage());
    }

    @Test
    void adminShouldHaveAccess() {
        User admin = new User("admin@test.com", VALID_PWD, Role.ADMIN);
        assertTrue(admin.canAccessAdminArea());
    }
}