package org.example.users;

import org.example.Input;

public class Register {
    private final Input scanner = new Input();
    String login;
    String password;
    String repeatPassword;
    long count = 0;

    public void createUser(){
        boolean isLoginUnique = false;
        boolean doesPasswordMatch = false;
        do {
            System.out.println("Enter login:");
            login = scanner.scannerText();
            count = ListOfUsers.listOfUsers().stream()
                    .filter(user -> user.login().equals(login))
                    .count();
            if (count != 0) {
                System.out.println("User already exists!");
            } else {
                do {
                    isLoginUnique = true;
                    System.out.println("Enter password:");
                    password = scanner.scannerText();
                    System.out.println("Repeat password:");
                    repeatPassword = scanner.scannerText();
                    if (password.equals(repeatPassword)) {
                        System.out.println("User created");
                        ListOfUsers.listOfUsers().add(new User(login, password));
                        doesPasswordMatch = true;
                    } else {
                        System.out.println("Wrong password");
                    }
                }while(!doesPasswordMatch);
            }
        }while(!isLoginUnique);
    }

    public void changePassword(){
        boolean isPasswordChanged = false;
        boolean isLoginCorrect = false;
        do {
            try {
                System.out.println("Enter login:");
                String login = scanner.scannerText();
                User thisUser = ListOfUsers.listOfUsers().stream()
                        .filter(user -> user.login().equals(login))
                        .toList().get(0);
                isLoginCorrect = true;
                do {
                    System.out.println("Enter old password:");
                    String oldPassword = scanner.scannerText();
                    if (thisUser.password().equals(oldPassword)) {
                        System.out.println("Enter new password:");
                        String newPassword = scanner.scannerText();
                        thisUser.setPassword(newPassword);
                        isPasswordChanged = true;
                        System.out.println("Password changed");
                    } else {
                        System.out.println("Wrong password");
                    }
                }while(!isPasswordChanged);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("User doesn't exists");
            }
        }while(!isLoginCorrect);
    }
}
