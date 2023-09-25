package org.example.users;

public class User extends Encryption {

    private String login;
    private String encryptedPassword;

    public User(String login, String password) {
        this.login = login;
        this.encryptedPassword = encrypt(password);
    }

    public String login() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String encryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
