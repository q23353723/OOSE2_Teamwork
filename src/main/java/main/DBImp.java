package main;

import model.Admin;
import model.Powerbank;
import model.User;

import java.util.ArrayList;

public interface DBImp {
    void insert(User user);
    User selectUser(String username);
    Admin selectAdmin(String username);
    long countPowerbank();
    ArrayList<Powerbank> getPowerbankList();
    Powerbank getPowerbank();
    Powerbank getPowerbank(String id);
    void update(User user, Powerbank powerbank);
}
