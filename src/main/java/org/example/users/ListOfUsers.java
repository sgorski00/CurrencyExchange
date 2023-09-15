package org.example.users;

import java.util.ArrayList;
import java.util.List;

public class ListOfUsers {
    private static List<User> listOfUsers = new ArrayList<>();

    static {
        User sgorski00 = new User("sgorski00", "12345");
        User latuszka = new User("latuszka", "Latka@@@");
        User daga33 = new User("daga33", "qwertyuiop");
        User yomenik = new User("yomenik", "zaq1@WSX");
        listOfUsers.add(sgorski00);
        listOfUsers.add(daga33);
        listOfUsers.add(latuszka);
        listOfUsers.add(yomenik);
    }

    public static List<User> listOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<User> listOfUsers) {
        ListOfUsers.listOfUsers = listOfUsers;
    }
}
