package form;

import com.jfoenix.controls.JFXButton;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.AppearCommand;
import main.DisappearCommand;
import main.TransformCommand;
import main.ViewManager;

public class MainView implements FxmlView<MainViewModel> {
    @FXML
    private JFXButton powerbankButton, borrowButton, returnButton;

    @FXML
    private ImageView menuButton;

    @FXML
    private AnchorPane buttonPane;

    private TransformCommand appearCommand, disappearCommand;

    @InjectViewModel //is provided by mvvmFX
    private MainViewModel viewModel;

    //will be called by JavaFX as soon as the FXML bootstrapping is done
    public void initialize(){
        //綁定這三個Button的disableProperty到viewModel的borrowedProperty
        powerbankButton.disableProperty().bindBidirectional(viewModel.borrowedProperty());
        borrowButton.disableProperty().bind(viewModel.borrowedProperty().not());
        returnButton.disableProperty().bind(viewModel.borrowedProperty());

        //宣告Command
        appearCommand = new AppearCommand(buttonPane);
        disappearCommand = new DisappearCommand(buttonPane);

        //執行Command
        disappearCommand.execute();

        //在menuButton新增Listener
        menuButton.setOnMouseClicked(event -> {
            if(!buttonPane.isVisible()) { //如果buttonPane是不可見的
                appearCommand.execute();
            }
            else {
                disappearCommand.execute(); //如果buttonPane是不可見的
            }
        });
    }

    //當borrow Button被點擊則Delegate給viewModel的borrow
    public void borrow() {
        viewModel.borrow();
    }

    //當turnbcak Button被點擊則Delegate給viewModel的turnbcak
    public void turnback() {
        viewModel.turnback();
    }

    //當點擊按鈕則切換頁面
    public void changeToUserInfoView() {
        ViewManager.getInstance().setRoot("UserInfoView");
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
