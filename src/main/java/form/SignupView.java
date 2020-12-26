package form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import main.ViewManager;

public class SignupView implements FxmlView<SignupViewModel> {
    @FXML
    public JFXButton signupButton;

    @FXML
    public JFXTextField email, username;

    @FXML
    public JFXPasswordField password;

    //注入SignupViewModel
    @InjectViewModel //is provided by mvvmFX
    private SignupViewModel viewModel;

    //初始化
    public void initialize() {
        viewModel.init();
        //綁定textProperty
        email.textProperty().bindBidirectional(viewModel.emailProperty());
        username.textProperty().bindBidirectional(viewModel.usernameProperty());
        password.textProperty().bindBidirectional(viewModel.passwordProperty());

        //綁定disableProperty
        signupButton.disableProperty()
                .bindBidirectional(viewModel.signupPossibleProperty());
    }

    //切換畫面
    public void register() {
        if(viewModel.register()) {
            ViewManager.getInstance().setRoot("LoginView");
        }
    }
    public void back() {
        ViewManager.getInstance().setRoot("LoginView");
    }
    //最小化
    public void minimize() { ViewManager.getInstance().minimize(); }
    //關閉視窗
    public void close() {
        ViewManager.getInstance().close();
    }
}
