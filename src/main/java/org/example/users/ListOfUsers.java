package org.example.users;

import java.util.ArrayList;
import java.util.List;

public class ListOfUsers {
    private static List<User> listOfUsers = new ArrayList<>();

    static {
        User sgorski00 = new User("sgorski00", "Koksu1@3456");
        listOfUsers().add(sgorski00);
    }

    public static List<User> listOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<User> listOfUsers) {
        ListOfUsers.listOfUsers = listOfUsers;
    }
}
