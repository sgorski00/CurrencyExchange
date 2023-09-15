package org.example.users;

import org.example.Input;
import org.example.menu.MainMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogIn{
    private static final Input scanner = new Input();
    private MainMenu menu = new MainMenu();
    public void logIn() {
        boolean isValid = false;
        String login;
        String password;
        while(!isValid){
            try {
                login = getLogin();
                password = getPassword();
                String finalLogin = login;
                String finalPassword = password;

                User loggedUser = ListOfUsers.listOfUsers().stream()
                        .filter(user -> user.login().equals(finalLogin))
                        .filter(user -> user.password().equals(finalPassword))
                        .toList().get(0);

                System.out.println("You are logged as " + loggedUser.login()+"\n");
                isValid = true;
            }catch(IndexOutOfBoundsException e){
                System.err.println("Wrong login or password");
                scanner.pressEnterToContinue();
            }
//            for (User userName:listOfUsers) {
//                if(login.equals(userName.login())){
//                    System.out.println("Enter password:");
//                    password = scanner.scannerText();
//                    if(password.equals(userName.password())){
//                        System.out.printf("%s %s!%n", "You are in", userName.login());
//                        System.out.println("====================");
//                        isValid = true;
//                    }
//                }else if(listOfUsers.contains())
//            }
        }
    }

    private static String getPassword() {
        String password;
        System.out.println("Enter password:");
        password = scanner.scannerText();
        System.out.println("====================");
        return password;
    }

    private static String getLogin() {
        String login;
        System.out.println("====================");
        System.out.println("Enter login:");
        login = scanner.scannerText();
        return login;
    }
}
