package org.example.menu;

import org.example.Input;
import org.example.users.ListOfUsers;
import org.example.users.Login;
import org.example.users.Register;
import org.example.users.User;

public class LoginMenu {
    private final Input scanner = new Input();
    private final ListOfTasks listOfTasks = new ListOfTasks();
    private final Login login = new Login();
    private final Register register = new Register();

    private static boolean goBack = false;

    public static void setGoBack(boolean goBack) {
        LoginMenu.goBack = goBack;
    }

    public void print(){
        int choice;
        do {
            listOfTasks.printLoginMenu();
            choice = scanner.scannerInt();
            switch (choice) {
                case 1 -> {
                    do {
                        login.logIn();
                    }while (!goBack && Login.loggedUser() == null);
                }

                case 2 -> register.createUser();

                case 3 -> register.changePassword();

                case 0 -> ListOfUsers.listOfUsers().stream()
                        .map(User::login)
                        .forEach(System.out::println);
                default -> System.out.println("Enter correct number");

            }
        } while (Login.loggedUser() == null);
    }
}
