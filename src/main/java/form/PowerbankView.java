package form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.AppearCommand;
import main.DisappearCommand;
import main.TransformCommand;
import main.ViewManager;

public class PowerbankView implements FxmlView<PowerbankViewModel> {
    @FXML
    public JFXButton mainButton, userinfoButton;

    @FXML
    private ImageView menuButton;

    @FXML
    private Label id, capacity;

    @FXML
    private JFXProgressBar capacityBar;

    @FXML
    private AnchorPane buttonPane;

    private TransformCommand appearCommand, disappearCommand;

    //注入PowerbankViewModel
    @InjectViewModel //is provided by mvvmFX
    private PowerbankViewModel viewModel;

    //初始化
    public void initialize(){
        //綁定Label的textProperty
        id.textProperty().bindBidirectional(viewModel.powebanIdPoperty());
        capacity.textProperty().bindBidirectional(viewModel.capacityPoperty());
        capacityBar.progressProperty().bindBidirectional(viewModel.capacityBarPoperty());

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

    //切換畫面
    public void changeToMainView() {
        ViewManager.getInstance().setRoot("MainView");
    }
    public void changeToUserInfoView() {
        ViewManager.getInstance().setRoot("UserInfoView");
    }
    public void logout() {
        ViewManager.getInstance().setRoot("LoginView");
    }
    //最小化
    public void minimize() { ViewManager.getInstance().minimize(); }
    //關閉視窗
    public void close() { ViewManager.getInstance().close(); }
}
