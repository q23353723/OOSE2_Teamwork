package form;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.Session;

public class UserInfoViewModel implements ViewModel {
    //宣告Property
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty username = new SimpleStringProperty("");
    private BooleanProperty borrowed = new SimpleBooleanProperty();

    //getter
    public StringProperty emailProperty() { return email; }
    public StringProperty usernameProperty() {
        return username;
    }
    public BooleanProperty borrowedProperty() { return borrowed;}

    //載入
    public void load() {
        //綁定borrowed Property
        borrowed.bind(Bindings.createBooleanBinding(() -> {
            //如果使用者沒有借用中的行動電源則disable
            if(Session.getInstance().getUser().powerbankIdProperty().get().equals("")){
                return true;
            } else return false;
        }, Session.getInstance().getUser().powerbankIdProperty()));
        email.set(Session.getInstance().getUser().getEmail());
        username.set(Session.getInstance().getUser().getAccount());
    }
}
