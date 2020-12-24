package form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import main.ViewManager;


public class LoginView implements FxmlView<LoginViewModel> {
    @FXML
    public JFXButton loginButton, adminLoginButton;

    @FXML
    public JFXTextField username;

    @FXML
    public JFXPasswordField password;

    //注入LoginViewModel
    @InjectViewModel //is provided by mvvmFX
    private LoginViewModel viewModel;

    //初始化
    //will be called by JavaFX as soon as the FXML bootstrapping is done
    public void initialize(){
        //LoginViewModel初始化
        viewModel.init();

        //綁定Label username和password的textProperty
        username.textProperty().bindBidirectional(viewModel.usernameProperty());
        password.textProperty().bindBidirectional(viewModel.passwordProperty());
        //綁定loginButton和adminLoginButton的disableProperty
        loginButton.disableProperty().bindBidirectional(viewModel.loginPossibleProperty());
        adminLoginButton.disableProperty().bindBidirectional(viewModel.loginPossibleProperty());
    }

    //如果viewModel login()回傳true則載入資訊且切換到MainView
    public void login() {
        if(viewModel.login()) {
            ((MainViewModel) ViewManager.getInstance().getViewModel("MainView")).load();
            ((UserInfoViewModel) ViewManager.getInstance().getViewModel("UserInfoView")).load();
            ViewManager.getInstance().setRoot("MainView");
        }
    }

    //如果viewModel adminLogin()回傳true則切換到AdminView
    public void adminLogin() {
        if(viewModel.adminLogin()) {
            ViewManager.getInstance().setRoot("AdminView");
        }
    }

    //當signup Label被點擊則切換到SignupView
    public void signUp() { ViewManager.getInstance().setRoot("SignupView"); }

    //當close Image被點時關閉視窗
    public void close() {
        ViewManager.getInstance().close();
    }

    //當minimize Image被點時最小化視窗
    public void minimize() { ViewManager.getInstance().minimize(); }
}
