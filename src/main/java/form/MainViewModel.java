package form;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.control.Alert;
import main.DBManager;
import main.MongoDBImp;
import main.Session;
import model.Powerbank;

public class MainViewModel implements ViewModel {
    DBManager dbm = new DBManager(new MongoDBImp());

    //宣告borrowedProperty
    private BooleanProperty borrowed = new SimpleBooleanProperty();

    //getter
    public BooleanProperty borrowedProperty() { return borrowed;}

    //載入
    public void load() {
        //綁定borrowed property
        borrowed.bind(Bindings.createBooleanBinding(() -> {
            //如果登入的使用者沒有借用中的行動電源
            if(Session.getInstance().getUser().powerbankIdProperty().get().equals("")){
                return true;
            } else return false;
        }, Session.getInstance().getUser().powerbankIdProperty()));

        //如果登入的user有借用中的行動電源
        if(!Session.getInstance().getUser().powerbankIdProperty().get().equals("")) {
            Session.getInstance().setPowerbank(dbm.getPowerbank(Session.getInstance().getUser().powerbankIdProperty().get()));
        }
    }

    //借行動電源
    public void borrow() {
        try {
            //從資料庫取出行動電源
            Powerbank powerbank = dbm.getPowerbank();
            //設定Available為false
            powerbank.setAvailable(false);
            //設定user的借用中pb的Id
            Session.getInstance().getUser().setPowerbankId(powerbank.getId());
            //更新資料到資料庫
            dbm.update(Session.getInstance().getUser(), powerbank);
            //將Powerbank設定到Session中
            Session.getInstance().setPowerbank(powerbank);
        } catch (Exception e) {
            //如果借用失敗
            Alert alert = new Alert(Alert.AlertType.ERROR, "沒有可借的行動電源");
            alert.setTitle("錯誤");
            alert.show();
        }
    }

    //還行動電源
    public void turnback() {
        Session.getInstance().getUser().setPowerbankId("");
        Session.getInstance().getPowerbank().setAvailable(true);
        dbm.update(Session.getInstance().getUser(), Session.getInstance().getPowerbank());
    }
}
