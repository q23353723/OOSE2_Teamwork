package main;

import de.saxsys.mvvmfx.FluentViewLoader;
import form.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //將TitleBar去除
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //從Singleton中getInstance
        ViewManager viewManager = ViewManager.getInstance();
        //將所有的ViewTuple新增到viewManager
        viewManager.addScene("LoginView", FluentViewLoader.fxmlView(LoginView.class).load());
        viewManager.addScene("MainView", FluentViewLoader.fxmlView(MainView.class).load());
        viewManager.addScene("SignupView", FluentViewLoader.fxmlView(SignupView.class).load());
        viewManager.addScene("UserInfoView", FluentViewLoader.fxmlView(UserInfoView.class).load());
        viewManager.addScene("PowerbankView", FluentViewLoader.fxmlView(PowerbankView.class).load());
        viewManager.addScene("AdminView", FluentViewLoader.fxmlView(AdminView.class).load());
        //顯示畫面
        viewManager.init(primaryStage);

        //每五秒鐘從資料庫更新Session中的資料
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            if((Session.getInstance().getPowerbank() != null) && (Session.getInstance().getUser() != null)) {
                                DBManager dbm = new DBManager(new MongoDBImp());
                                if(!Session.getInstance().getUser().powerbankIdProperty().get().equals("")) {
                                    System.out.println(Session.getInstance().getUser().powerbankIdProperty().get());
                                    Session.getInstance().setPowerbank(dbm.getPowerbank(Session.getInstance().getUser().powerbankIdProperty().get()));
                                }
                                ((MainViewModel) ViewManager.getInstance().getViewModel("MainView")).load();
                                ((PowerbankViewModel) ViewManager.getInstance().getViewModel("PowerbankView")).load();
                            }
                            //((AdminViewModel) ViewManager.getInstance().getViewModel("AdminView")).load();
                        });
                    }
                }, 0, 5000);
    }

    public static void main(String[] args) {
        launch(args);
    }
}