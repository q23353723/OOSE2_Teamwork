package form;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import main.FormattingStrategy;
import main.GraphicFormattingStrategy;
import main.PlainTextFormattingStrategy;
import main.ViewManager;

import java.util.ArrayList;

public class AdminView implements FxmlView<AdminViewModel> {
    @FXML
    private ImageView img1, img2, img3, img4, img5, img6;

    @FXML
    private Label label1, label2, label3, label4, label5, label6;

    @FXML
    private ToggleButton changeFormat;

    //宣告ImageView和Label的ArrayList
    private ArrayList<ImageView> imgList = new ArrayList<>();
    private ArrayList<Label> labelList = new ArrayList<>();

    //宣告FormattingStrategy
    private FormattingStrategy strategy = new GraphicFormattingStrategy(this);

    //注入AdminViewModel
    @InjectViewModel //is provided by mvvmFX
    private AdminViewModel viewModel;

    //初始化
    public void initialize() {
        //ViewModel初始化
        viewModel.load();
        //綁定各個Label的textProperty
        label1.textProperty().bind(viewModel.label1Property());
        label2.textProperty().bind(viewModel.label2Property());
        label3.textProperty().bind(viewModel.label3Property());
        label4.textProperty().bind(viewModel.label4Property());
        label5.textProperty().bind(viewModel.label5Property());
        label6.textProperty().bind(viewModel.label6Property());

        //將各個ImageView和Label加入ArrayList
        imgList.add(img1);
        imgList.add(img2);
        imgList.add(img3);
        imgList.add(img4);
        imgList.add(img5);
        imgList.add(img6);
        labelList.add(label1);
        labelList.add(label2);
        labelList.add(label3);
        labelList.add(label4);
        labelList.add(label5);
        labelList.add(label6);

        //Delegate給Strategy做排版
        strategy.format();

        //在changeFormat這個Button上新增Listener，如果Button狀態切換就切換Strategy並重新排版
        changeFormat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(changeFormat.isSelected()) {
                    setStrategy(new PlainTextFormattingStrategy((AdminView) ViewManager.getInstance().getView("AdminView").getCodeBehind()));
                } else {
                    setStrategy(new GraphicFormattingStrategy((AdminView) ViewManager.getInstance().getView("AdminView").getCodeBehind()));
                }
                strategy.format();
            }
        });
    }

    //setter
    public void setStrategy(FormattingStrategy strategy) {
        this.strategy = strategy;
    }

    //getter
    public ArrayList<ImageView> getImgList() {
        return imgList;
    }

    public ArrayList<Label> getLabelList() {
        return labelList;
    }

    //當close Image被點時關閉視窗
    public void close() {
        ViewManager.getInstance().close();
    }

    //當minimize Image被點時最小化視窗
    public void minimize() { ViewManager.getInstance().minimize(); }
}
