package model;

public abstract class Member {
    protected String account, password, email;

    public String getAccount() {
        return account;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
}
