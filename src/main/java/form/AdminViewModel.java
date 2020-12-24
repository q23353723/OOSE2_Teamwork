package form;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.DBManager;
import main.MongoDBImp;
import model.Powerbank;

import java.util.ArrayList;

public class AdminViewModel implements ViewModel {
    private DBManager dbm = new DBManager(new MongoDBImp());

    //宣告各Label的Property
    private StringProperty label1Property = new SimpleStringProperty();
    private StringProperty label2Property = new SimpleStringProperty();
    private StringProperty label3Property = new SimpleStringProperty();
    private StringProperty label4Property = new SimpleStringProperty();
    private StringProperty label5Property = new SimpleStringProperty();
    private StringProperty label6Property = new SimpleStringProperty();

    //初始化
    public void load() {
        ArrayList<Powerbank> list = dbm.getPowerbankList();
        label1Property.set("編號1:" + ((list.get(0).getAvailable()) ? "Available" : "Borrowed"));
        label2Property.set("編號2:" + ((list.get(1).getAvailable()) ? "Available" : "Borrowed"));
        label3Property.set("編號3:" + ((list.get(2).getAvailable()) ? "Available" : "Borrowed"));
        label4Property.set("編號4:" + ((list.get(3).getAvailable()) ? "Available" : "Borrowed"));
        label5Property.set("編號5:" + ((list.get(4).getAvailable()) ? "Available" : "Borrowed"));
        label6Property.set("編號6:" + ((list.get(5).getAvailable()) ? "Available" : "Borrowed"));
    }

    //getter
    public StringProperty label1Property() {
        return label1Property;
    }
    public StringProperty label2Property() {
        return label2Property;
    }
    public StringProperty label3Property() {
        return label3Property;
    }
    public StringProperty label4Property() {
        return label4Property;
    }
    public StringProperty label5Property() {
        return label5Property;
    }
    public StringProperty label6Property() {
        return label6Property;
    }

}
