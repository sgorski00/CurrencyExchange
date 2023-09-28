package org.example.users;

import org.example.Input;
import org.example.wallet.operations.ConvertCurrency;

public class LogIn extends Encryption{
    private static final Input scanner = new Input();
    private static User loggedUser;

    public static User loggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        LogIn.loggedUser = loggedUser;
    }

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

                setLoggedUser(ListOfUsers.listOfUsers().stream()
                        .filter(user -> user.login().equals(finalLogin))
                        .filter(user -> user.encryptedPassword().equals(encrypt(finalPassword)))
                        .toList().get(0));

                System.out.println("You are logged as " + loggedUser().login()+"\n");
                ConvertCurrency.setLoggedUserWallet(loggedUser().wallet().currencies());
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
