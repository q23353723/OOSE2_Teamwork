package main;

import model.Admin;
import model.Powerbank;
import model.User;
import java.util.ArrayList;

public class DBManager {
    DBImp imp;

    public DBManager(DBImp imp) {
        this.imp = imp;
    }

    public void insert(User user) {
        imp.insert(user);
    }
    public User selectUser(String username) {
        return imp.selectUser(username);
    }
    public Admin selectAdmin(String username) { return imp.selectAdmin(username); }
    public long countPowerbank() {
        return imp.countPowerbank();
    }
    public ArrayList<Powerbank> getPowerbankList() {
        return imp.getPowerbankList();
    }
    public Powerbank getPowerbank() {
        return imp.getPowerbank();
    }
    public Powerbank getPowerbank(String id) {
        return imp.getPowerbank(id);
    }
    public void update(User user, Powerbank powerbank) {
        imp.update(user, powerbank);
    }
}
