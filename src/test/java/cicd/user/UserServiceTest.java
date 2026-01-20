package cicd.user;

import com.devops.cicd.user.Role;
import com.devops.cicd.user.User;
import com.devops.cicd.user.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService userService = new UserService();
    private final String VALID_PWD = "Password123!";

    @Test
    void shouldRegisterValidUser() {
        User user = userService.register("service@test.com", VALID_PWD, Role.USER);
        assertNotNull(user);
        assertEquals("service@test.com", user.getEmail());
    }

    @Test
    void shouldPropagateExceptionWhenDataIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                userService.register("invalid-email", VALID_PWD, Role.USER));
    }
}