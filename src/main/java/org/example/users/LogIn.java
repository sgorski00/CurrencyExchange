package org.example.users;

import org.example.Input;

public class LogIn extends Encryption{
    private static final Input scanner = new Input();
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
                        .filter(user -> user.encryptedPassword().equals(encrypt(finalPassword)))
                        .toList().get(0);

                System.out.println("You are logged as " + loggedUser.login()+"\n");
                isValid = true;
            }catch(IndexOutOfBoundsException e){
                System.err.println("Wrong login or password");
                scanner.pressEnterToContinue();
            }
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
