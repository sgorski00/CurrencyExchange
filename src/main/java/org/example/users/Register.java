package org.example.users;

import org.example.Input;

public class Register extends Encryption{
    private final Input scanner = new Input();
    private String login;
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
                    String password = scanner.scannerText();
                    System.out.println("Repeat password:");
                    String repeatPassword = scanner.scannerText();
                    if (password.equals(repeatPassword) && Validation.isValidPassword(password)) {
                        System.out.println("User created");
                        ListOfUsers.listOfUsers().add(new User(login, password));
                        doesPasswordMatch = true;
                        scanner.pressEnterToContinue();
                    } else {
                        if(!(password.equals(repeatPassword))) {
                            System.err.println("Wrong password");
                        }else{
                            System.err.println("Password does not meet the reqiurements.");
                        }
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
                    if (thisUser.encryptedPassword().equals(encrypt(oldPassword))) {
                        System.out.println("Enter new password:");
                        String newPassword = scanner.scannerText();
                        if (Validation.isValidPassword(newPassword)) {
                            thisUser.setEncryptedPassword(encrypt(newPassword));
                            isPasswordChanged = true;
                            System.out.println("Password changed");
                        }else{
                            System.err.println("Password does not meet the reqiurements.");
                        }
                    } else {
                        System.err.println("Wrong password");
                    }
                }while(!isPasswordChanged);
            } catch (IndexOutOfBoundsException e) {
                System.err.println("User doesn't exists");
            }
        }while(!isLoginCorrect);
    }
}
