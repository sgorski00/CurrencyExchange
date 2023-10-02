package org.example.users;

import org.example.wallet.Wallet;

public class User extends Encryption {

    private final String login;
    private String encryptedPassword;
    private Wallet wallet;

    public User(String login, String password, Wallet wallet) {
        this.login = login;
        this.encryptedPassword = encrypt(password);
        this.wallet = wallet;
    }

    public String login() {
        return login;
    }

    public String encryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Wallet wallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
