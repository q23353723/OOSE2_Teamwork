import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses( { LoginViewModelTest.class, SignupViewModelTest.class } )
public class TestSuite {
}

