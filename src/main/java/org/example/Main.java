package org.example;

import org.example.menu.LoginMenu;
import org.example.menu.MainMenu;

public class Main {
    private static final MainMenu menu = new MainMenu();
    private static final LoginMenu login = new LoginMenu();
    //    private static final Map<User, Wallet> walletOfUser = new HashMap<>();

    public static void main(String[] args) {
        while(true) {
            login.print();
            menu.print();
        }
    }
}