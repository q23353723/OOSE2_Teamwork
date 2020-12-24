package model;

public class Admin extends Member{

    public void set(String account, String password, String email) {
        this.account = account;
        this.password = password;
        this.email = email;
    }
}
