package form;

import de.saxsys.mvvmfx.ViewModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import main.*;
import model.User;

import java.util.concurrent.Callable;


public class SignupViewModel implements ViewModel {
    //宣告Property
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty username = new SimpleStringProperty("");
    private StringProperty password = new SimpleStringProperty("");
    private BooleanProperty signupPossible = new SimpleBooleanProperty();

    //getter
    public StringProperty emailProperty() {
        return email;
    }
    public StringProperty usernameProperty() {
        return username;
    }
    public StringProperty passwordProperty() {
        return password;
    }
    public BooleanProperty signupPossibleProperty() {
        return signupPossible;
    }

    //初始化
    public void init() {
        //綁定signupPossible Property
        signupPossible.bind(Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                //如果有其中一欄是空的就Disable
                if (emailProperty().get().equals("") || username.get().equals("") || password.get().equals("")) return true;
                else return false;
            }
        }, email, username, password));
    }

    //註冊
    public boolean register() {
        try {
            if(username.get().equals("") || password.get().equals("") || email.get().equals("")) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "欄位有空值");
                    alert.setTitle("註冊失敗");
                    alert.show();
                });
                return false;
            }
            else {
                DBManager dbm = new DBManager(new MongoDBImp());
                //建立新的User
                MemberFactory factory = new UserFactory();
                User user = (User) factory.createMember();
                user.set(username.get(), password.get(), email.get(), "");
                //將user新增到資料庫中
                dbm.insert(user);
                return true;
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Null");
                alert.setTitle("註冊失敗");
                alert.show();
            });
            return false;
        }
    }
}
