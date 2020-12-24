package main;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class ViewManager {
    private static ViewManager viewManager;
    private HashMap<String, ViewTuple> ViewTupleMap = new HashMap<>();

    private Stage stage;

    private double x,y = 0;

    private ViewManager(){}

    public static ViewManager getInstance() {
        if(viewManager == null) {
            viewManager = new ViewManager();
        }
        return viewManager;
    }

    //初始介面
    public void init(Stage stage) {
        this.stage = stage;
        Parent root = ViewTupleMap.get("LoginView").getView();
        stage.setScene(new Scene(root, 1024, 768));
        stage.show();
    }

    public void addScene(String name, ViewTuple view){
        Parent root = view.getView();
        //設定拖動視窗的動作
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        //將root放進HashMap中
        ViewTupleMap.put(name, view);
    }

    //設定Scene
    public void setScene(String name) {
        Parent root = ViewTupleMap.get(name).getView();
        stage.setScene(new Scene(root, 1024, 768));
        stage.show();
    }

    //設定Root
    public void setRoot(String name) {
        Parent root = ViewTupleMap.get(name).getView();
        stage.getScene().setRoot(root);
    }

    //關閉視窗
    public void close() {
        stage.close();
    }
    //最小化
    public void minimize() { stage.setIconified(true);}
    //getter
    public ViewTuple getView(String name) {
        return ViewTupleMap.get(name);
    }

    public ViewModel getViewModel(String name) {
        return ViewTupleMap.get(name).getViewModel();
    }
}
