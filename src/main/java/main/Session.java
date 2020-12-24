package main;

import model.Powerbank;
import model.User;

public class Session {
    private static Session session;
    private User user;
    private Powerbank powerbank;

    private Session() {}

    public static Session getInstance() {
        if(session == null) {
            session = new Session();
        }
        return session;
    }

    //setter
    public void setUser(User u) {
        user = u;
    }
    public void setPowerbank(Powerbank pwb) { powerbank = pwb; }
    //getter
    public User getUser() { return user; }
    public Powerbank getPowerbank() {
        return powerbank;
    }
}
