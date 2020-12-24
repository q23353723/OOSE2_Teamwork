package form;

import com.jfoenix.controls.JFXButton;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.*;

public class UserInfoView implements FxmlView<UserInfoViewModel> {
    @FXML
    private JFXButton powerbankButton;

    @FXML
    private ImageView menuButton;
    
    @FXML
    private AnchorPane buttonPane;

    @FXML
    private Label email, username;

    private TransformCommand appearCommand, disappearCommand;

    //注入UserInfoViewModel
    @InjectViewModel //is provided by mvvmFX
    private UserInfoViewModel viewModel;

    //初始化
    public void initialize() {
        //綁定disableProperty
        powerbankButton.disableProperty().bindBidirectional(viewModel.borrowedProperty());

        //綁定textProperty
        email.textProperty().bind(viewModel.emailProperty());
        username.textProperty().bind(viewModel.usernameProperty());

        //宣告Command
        appearCommand = new AppearCommand(buttonPane);

        disappearCommand = new DisappearCommand(buttonPane);

        //在menuButton新增Listener
        menuButton.setOnMouseClicked(event -> {
            if(!buttonPane.isVisible()) {
                appearCommand.execute();
            }
            else {
                disappearCommand.execute();
            }
        });
    }

    //切換頁面
    public void changeToMainView() {
        ViewManager.getInstance().setRoot("MainView");
    }
    public void changeToPowerbankView() {
        ViewManager.getInstance().setRoot("PowerbankView");
        ((PowerbankViewModel) ViewManager.getInstance().getViewModel("PowerbankView")).load();
    }
    public void logout() {
        ViewManager.getInstance().setRoot("LoginView");
    }
    //最小化
    public void minimize() { ViewManager.getInstance().minimize(); }
    //關閉視窗
    public void close() { ViewManager.getInstance().close(); }
}
