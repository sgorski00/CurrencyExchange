package org.example.menu;

import org.example.Input;
import org.example.users.LogIn;
import org.example.users.User;
import org.example.wallet.Wallet;
import org.example.wallet.currencies.Currency;
import org.example.wallet.operations.BasicOperations;
import org.example.wallet.operations.ConvertCurrency;
import org.example.wallet.operations.OperationsOnWallet;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public class MainMenu extends ListOfTasks {
    private final BasicOperations basic = new BasicOperations();
    private final ConvertCurrency conversion = new ConvertCurrency();
    Wallet wallet = new Wallet();
    Input scanner = new Input();
    private User thisUser;

    public void print() {
        int choice;
        thisUser = LogIn.loggedUser();
        do {
            printTasks();
            choice = scanner.scannerInt();
            switch (choice) {
                case 1 -> {
                    wallet.printListOfUsersCurrencies();
                    scanner.pressEnterToContinue();
                }
                case 2 -> {
                    System.out.println("Select the currency, in which You want to see your total account balance.");
                    wallet.printNumericListOfUsersCurrencies();
                    int choosedCurrency = scanner.scannerInt();
                    conversion.getSumOfMoney(choosedCurrency);
                    scanner.pressEnterToContinue();
                }
                case 3 -> {
                    wallet.printNumericListOfUsersCurrencies();
                    int choosedCurrency = scanner.scannerInt();
                    System.out.println("Enter amount of money:");
                    BigDecimal amount = scanner.scannerNumber();
                    if(thisUser.wallet().currencies().containsKey(choosedCurrency)) {
                        doOperation(choosedCurrency, amount, basic.addMoney(amount, thisUser.wallet().currencies().get(choosedCurrency).currency()));
                    }else{
                        System.err.println("Enter correct number. That currency doesn't exists.");
                    }
                    scanner.pressEnterToContinue();
                }
                case 4 -> {
                    wallet.printNumericListOfUsersCurrencies();
                    int choosedCurrency = scanner.scannerInt();
                    System.out.println("Enter amount of money:");
                    BigDecimal amount = scanner.scannerNumber();
                    if(thisUser.wallet().currencies().containsKey(choosedCurrency)) {
                        doOperation(choosedCurrency, amount, basic.removeMoney(amount, thisUser.wallet().currencies().get(choosedCurrency).currency()));
                    }else{
                        System.err.println("Enter correct number. That currency doesn't exists.");
                    }
                    scanner.pressEnterToContinue();
                }
                case 5 -> {
                    wallet.printNumericListOfUsersCurrencies();
                    int choosedCurrency = scanner.scannerInt();
                    System.out.println("Enter amount of money:");
                    BigDecimal amount = scanner.scannerNumber();
                    if (thisUser.wallet().currencies().containsKey(choosedCurrency)) {
                        Money temp = basic.setMoney(amount, thisUser.wallet().currencies().get(choosedCurrency).currency());
                        thisUser.wallet().currencies().get(choosedCurrency).setCurrency(temp);
                        System.out.printf("%s %s %s %s%n", "The amount of", thisUser.wallet().currencies().get(choosedCurrency).currency().getCurrency(), "has been set to", amount);
                    } else {
                        System.err.println("That currency doesn't exist!");
                    }
                    scanner.pressEnterToContinue();
                }
                case 6 -> {
                    System.out.println("Convert currency from:");
                    wallet.printNumericListOfUsersCurrencies();
                    int firstCu = scanner.scannerInt();
                    System.out.println("Enter amount of money to convert:");
                    BigDecimal amount = scanner.scannerNumber();
                    System.out.println("Convert currency to:");
                    wallet.printNumericListOfUsersCurrencies();
                    int secondCu = scanner.scannerInt();
                    if (thisUser.wallet().currencies().containsKey(firstCu) && thisUser.wallet().currencies().containsKey(secondCu)) {
                        conversion.convertCurrency(firstCu, secondCu, amount);
                        wallet.printListOfUsersCurrencies();
                    } else {
                        System.err.println("That currency doesn't exist!");
                    }
                    scanner.pressEnterToContinue();
                }
                case 7 -> {
                    System.out.println("Select default currency:");
                    wallet.printNumericListOfUsersCurrencies();
                    int currency = scanner.scannerInt();
                    if (thisUser.wallet().currencies().containsKey(currency)) {
                        conversion.printExchangeRates(currency);
                    } else {
                        System.err.println("That currency doesn't exist!");
                    }
                    scanner.pressEnterToContinue();
                }
                case 8 ->{
                    printWalletMenu();
                    int choose = scanner.scannerInt();
                    OperationsOnWallet operations = new OperationsOnWallet();
                    switch(choose){
                        case 1 -> operations.addCurrencyToWallet(thisUser);
                        case 2 -> operations.removeCurrencyFromWallet(thisUser);
                    }
                }
                case 9 -> {
                    System.out.println("You are logged out");
                    choice = 0;
                }
                case 0 -> {
                    System.out.println("The program has ended");
                    System.exit(0);
                }
            }
        } while (choice != 0);
    }

    private void doOperation(int choosedCurrency, BigDecimal amount, Money operation) {
        if (amount.intValue()>0) {
            thisUser.wallet().currencies().get(choosedCurrency).setCurrency(operation);
            System.out.printf("%s %s %s%n", "Your account balance has been changed by", amount, thisUser.wallet().currencies().get(choosedCurrency).currency().getCurrency());
        } else {
            System.err.println("Amount of money must be larger than 0!");
        }
    }
}
