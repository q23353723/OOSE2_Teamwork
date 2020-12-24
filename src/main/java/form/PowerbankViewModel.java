package form;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;
import main.Session;

public class PowerbankViewModel implements ViewModel {
    //宣告Property
    private StringProperty powerbankId = new SimpleStringProperty();
    private StringProperty capacity = new SimpleStringProperty();
    private DoubleProperty capacityBar = new SimpleDoubleProperty();

    //載入
    public void load() {
        //綁定Property
        powerbankId.bind(Session.getInstance().getPowerbank().idProperty());
        capacity.bind(Session.getInstance().getPowerbank().capacityProperty());
        capacityBar.bind(Session.getInstance().getPowerbank().capacityBarProperty());
    }

    //getter
    public StringProperty powebanIdPoperty() {
        return powerbankId;
    }
    public StringProperty capacityPoperty() {
        return capacity;
    }
    public DoubleProperty capacityBarPoperty() {
        return capacityBar;
    }
}
