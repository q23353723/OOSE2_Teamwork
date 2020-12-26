import com.sun.javafx.application.PlatformImpl;
import form.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupViewModelTest {
    SignupViewModel signupViewModel = new SignupViewModel();

    @BeforeEach
    public void setup(){
        PlatformImpl.startup(()->{});
    }

    @ParameterizedTest
    @MethodSource("testEachSignup")
    public void testLogin(String email, String username, String password, boolean result) {
        signupViewModel.emailProperty().set(email);
        signupViewModel.usernameProperty().set(username);
        signupViewModel.passwordProperty().set(password);

        assertEquals(result, signupViewModel.register());
    }

    static Stream<Arguments> testEachSignup() {
        return Stream.of(
                Arguments.of("test@gmail.com", "Test", "test", true),
                Arguments.of("", "hey", "you", false),
                Arguments.of("test@gmail.com", "", "you", false),
                Arguments.of("test@gmail.com", "hey", "", false),
                Arguments.of("", "", "", false),
                Arguments.of("test@gmail.com", "hey", null, false),
                Arguments.of("test@gmail.com", null, "you", false),
                Arguments.of(null, "hey", "you", false),
                Arguments.of(null, null, null, false)
        );
    }
}
