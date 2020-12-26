import com.sun.javafx.application.PlatformImpl;
import de.saxsys.javafx.test.JfxRunner;
import form.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JfxRunner.class)
public class LoginViewModelTest {
    LoginViewModel loginViewModel = new LoginViewModel();

    @BeforeEach
    public void setup(){
        PlatformImpl.startup(()->{});
    }

    @ParameterizedTest
    @MethodSource("testEveryUser")
    public void testLogin(String username, String password, boolean result) {
        loginViewModel.usernameProperty().set(username);
        loginViewModel.passwordProperty().set(password);

        assertEquals(result, loginViewModel.login());
    }

    static Stream<Arguments> testEveryUser() {
        return Stream.of(
                Arguments.of("abc123", "abc123", true),
                Arguments.of("abc123", "123456", false),
                Arguments.of("hey", "you", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testEveryAdmin")
    public void testAdminLogin(String username, String password, boolean result) {
        loginViewModel.usernameProperty().set(username);
        loginViewModel.passwordProperty().set(password);

        assertEquals(result, loginViewModel.adminLogin());
    }

    static Stream<Arguments> testEveryAdmin() {
        return Stream.of(
                Arguments.of("admin", "admin", true),
                Arguments.of("admin", "123456", false),
                Arguments.of("hey", "you", false)
        );
    }
}
