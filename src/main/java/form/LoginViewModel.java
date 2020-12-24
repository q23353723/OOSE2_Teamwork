package form;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import main.DBManager;
import main.MongoDBImp;
import main.Session;
import model.Admin;
import model.User;

import java.util.concurrent.Callable;


public class LoginViewModel implements ViewModel {
    //宣告Property
    private StringProperty username = new SimpleStringProperty("");
    private StringProperty password = new SimpleStringProperty("");
    private BooleanProperty loginPossible = new SimpleBooleanProperty();
    private DBManager dbm = new DBManager(new MongoDBImp());
    private User user;
    private Admin admin;

    //getter
    public StringProperty usernameProperty() {
        return username;
    }
    public StringProperty passwordProperty() { return password; }
    public BooleanProperty loginPossibleProperty() {
        return loginPossible;
    }

    //初始化
    public void init() {
        loginPossible.bind(Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                if (username.get().equals("") || password.get().equals("")) return true;
                else return false;
            }
        }, username, password));
    }

    //登入
    public boolean login() {
        System.out.println("Pressed!");
        try {
            //從資料庫取出username == 輸入在username中的值的資料
            user = dbm.selectUser(username.get());
            //如果輸入的密碼和從資料庫中取出的user密碼相同
            if(password.get().equals(user.getPassword())) {
                System.out.println("Login Success!");
                System.out.println(user.getAccount());
                System.out.println("pid:" + user.powerbankIdProperty().get());
                //將user設定到Session中
                Session.getInstance().setUser(user);
                Session.getInstance().setPowerbank(dbm.getPowerbank(Session.getInstance().getUser().powerbankIdProperty().get()));
                System.out.println(Session.getInstance().getUser().powerbankIdProperty().get().equals(""));
                return true;
            }
            else {
                //如果密碼錯誤
                Alert alert = new Alert(Alert.AlertType.ERROR, "帳號或密碼錯誤");
                alert.setTitle("錯誤");
                alert.show();
                return false;
            }
        } catch (Exception e) {
            //如果資料庫中沒有取到這個username的user
            Alert alert = new Alert(Alert.AlertType.ERROR, "帳號不存在");
            alert.setTitle("帳號錯誤");
            alert.show();
            return false;
        }
    }

    //管理員登入
    public boolean adminLogin() {
        try {
            //從資料庫中取得這個username的admin
            admin = dbm.selectAdmin(username.get());
            //如果密碼正確
            if (password.get().equals(admin.getPassword())) {
                System.out.println("Login Success!");
                System.out.println(admin.getAccount());
                return true;
            } else {
                //如果密碼錯誤
                Alert alert = new Alert(Alert.AlertType.ERROR, "帳號或密碼錯誤");
                alert.setTitle("錯誤");
                alert.show();
                return false;
            }
        } catch (Exception e) {
            //如果沒取到資料
            Alert alert = new Alert(Alert.AlertType.ERROR, "帳號不存在");
            alert.setTitle("帳號錯誤");
            alert.show();
            return false;
        }
    }
}
