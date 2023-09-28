package org.example.users;

import org.example.Input;
import org.example.wallet.Wallet;
import org.example.wallet.currencies.Currency;
import org.javamoney.moneta.Money;

import java.util.ArrayList;
import java.util.List;

public class Register extends Encryption{
    private final Input scanner = new Input();
    private Wallet wallet = new Wallet();
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
                    if (encrypt(password).equals(encrypt(repeatPassword)) && Validation.isValidPassword(password)) {
                        System.out.println("Password correct.");
                        Wallet wallet1 = createWallet();
                        System.out.println("User created");
                        ListOfUsers.listOfUsers().add(new User(login, password, wallet1));
                        doesPasswordMatch = true;
                        scanner.pressEnterToContinue();
                    } else {
                        if(!(encrypt(password).equals(encrypt(repeatPassword)))) {
                            System.err.println("Wrong password");
                        }else{
                            System.err.println("Password does not meet the reqiurements.");
                        }
                    }
                }while(!doesPasswordMatch);
            }
        }while(!isLoginUnique);
    }

    private Wallet createWallet() {
        System.out.println("Choose currencies for start:");
        wallet.printListOfCurrencies();
        List<Currency> list = new ArrayList<>();
        int choose = 1;
        while(choose!=0) {
            choose = scanner.scannerInt();
            try {
                System.out.println("Added: " + Wallet.listOfCurrencies().get(choose).currency().getCurrency());
                Currency cur = new Currency(Wallet.listOfCurrencies().get(choose).currency().getCurrency().toString(), 0) {
                    @Override
                    public Money currency() {
                        return super.currency();
                    }
                };
                list.add(cur);
            }catch (NullPointerException e){
                break;
            }
            System.out.println("Choose another currency or enter '0' to end adding currencies to wallet.");
        }

        return new Wallet(list.toArray(new Currency[0]));
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
