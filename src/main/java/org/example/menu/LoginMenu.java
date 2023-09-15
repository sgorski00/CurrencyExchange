package org.example.menu;

import org.example.Input;
import org.example.users.ListOfUsers;
import org.example.users.LogIn;
import org.example.users.Register;
import org.example.users.User;

public class LoginMenu {
    private final Input scanner = new Input();
    private final ListOfTasks listOfTasks = new ListOfTasks();
    private final LogIn login = new LogIn();
    private final Register register = new Register();

    public void print() {
        int choice;
        boolean isEnded = false;
        do {
            listOfTasks.printLoginMenu();
            choice = scanner.scannerInt();
            switch (choice) {
                case 1 -> {
                    login.logIn();
                    isEnded = true;
                }
                case 2 -> register.createUser();

                case 3 -> register.changePassword();

                case 0 -> {
                    ListOfUsers.listOfUsers().stream()
                            .map(User::login)
                            .forEach(System.out::println);
                }
                default -> System.out.println("Enter correct number");

            }
        } while (!isEnded);
    }
}
