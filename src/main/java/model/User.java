package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.bson.types.ObjectId;

import java.util.List;

public class User extends Member {
    private StringProperty powerbankId = new SimpleStringProperty();

    public void set(String account, String password, String email, String powerbankId) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.powerbankId.set(powerbankId);
    }

    public StringProperty powerbankIdProperty() { return powerbankId; }

    public void setPowerbankId(String id) {
        this.powerbankId.set(id);
    }

    public void printDetail() {
        System.out.println("username = " + account);
        System.out.println("password = " + password);
        System.out.println("email = " + email);
    }
}
