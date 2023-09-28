package org.example.users;

import org.example.wallet.Wallet;
import org.example.wallet.currencies.EUR;
import org.example.wallet.currencies.PLN;
import org.example.wallet.currencies.USD;

import java.util.ArrayList;
import java.util.List;

public class ListOfUsers {
    private static List<User> listOfUsers = new ArrayList<>();

    static {
        User sgorski00 = new User("sgorski00", "Password!23",
                new Wallet(new EUR(0), new PLN(0), new USD(0)));
        listOfUsers().add(sgorski00);
    }

    public static List<User> listOfUsers() {
        return listOfUsers;
    }
}
