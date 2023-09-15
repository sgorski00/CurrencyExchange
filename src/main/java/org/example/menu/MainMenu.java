package org.example.menu;

import org.example.Input;
import org.example.wallet.Operations;
import org.example.wallet.Wallet;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public class MainMenu extends ListOfTasks {
    private final Operations op = new Operations();
    Wallet wallet = new Wallet();
    Input scanner = new Input();

    public void print() {
        int choice;
        do {
            printTasks();
            choice = scanner.scannerInt();
            switch (choice) {
                case 1 -> {
                    wallet.printListOfAmmountOfCurrencies();
                    scanner.pressEnterToContinue();
                }
                case 2 -> {
                    System.out.println("Select the currency, in which You want to see your total account balance.");
                    wallet.printListOfCurrencies();
                    int choosedCurrency = scanner.scannerInt();
                    op.getSumOfMoney(choosedCurrency);
                    scanner.pressEnterToContinue();
                }
                case 3 -> {
                    wallet.printListOfCurrencies();
                    int choosedCurrency = scanner.scannerInt();
                    System.out.println("Enter amount of money:");
                    BigDecimal amount = scanner.scannerNumber();
                    if(Wallet.listOfCurrencies().containsKey(choosedCurrency)) {
                        doOperation(choosedCurrency, amount, op.addMoney(amount, Wallet.listOfCurrencies().get(choosedCurrency)));
                    }else{
                        System.err.println("Enter correct number. That currency doesn't exists.");
                    }
                    scanner.pressEnterToContinue();
                }
                case 4 -> {
                    wallet.printListOfCurrencies();
                    int choosedCurrency = scanner.scannerInt();
                    System.out.println("Enter amount of money:");
                    BigDecimal amount = scanner.scannerNumber();
                    if(Wallet.listOfCurrencies().containsKey(choosedCurrency)) {
                        doOperation(choosedCurrency, amount, op.removeMoney(amount, Wallet.listOfCurrencies().get(choosedCurrency)));
                    }else{
                        System.err.println("Enter correct number. That currency doesn't exists.");
                    }
                    scanner.pressEnterToContinue();
                }
                case 5 -> {
                    wallet.printListOfCurrencies();
                    int choosedCurrency = scanner.scannerInt();
                    System.out.println("Enter amount of money:");
                    BigDecimal amount = scanner.scannerNumber();
                    if (Wallet.listOfCurrencies().containsKey(choosedCurrency)) {
                        Money temp = op.setMoney(amount, Wallet.listOfCurrencies().get(choosedCurrency));
                        Wallet.listOfCurrencies().put(choosedCurrency, temp);
                        System.out.printf("%s %s %s %s%n", "The amount of", Wallet.listOfCurrencies().get(choosedCurrency).getCurrency(), "has been set to", amount);
                    } else {
                        System.err.println("That currency doesn't exist!");
                    }
                    scanner.pressEnterToContinue();
                }
                case 6 -> {
                    System.out.println("Convert currency from:");
                    wallet.printListOfAmmountOfCurrencies();
                    int firstCu = scanner.scannerInt();
                    System.out.println("Enter amount of money to convert:");
                    BigDecimal amount = scanner.scannerNumber();
                    System.out.println("Convert currency to:");
                    wallet.printListOfAmmountOfCurrencies();
                    int secondCu = scanner.scannerInt();
                    if (Wallet.listOfCurrencies().containsKey(firstCu) && Wallet.listOfCurrencies().containsKey(secondCu)) {
                        op.convertCurrency(firstCu, secondCu, amount);
                        wallet.printListOfAmmountOfCurrencies();
                    } else {
                        System.err.println("That currency doesn't exist!");
                    }
                    scanner.pressEnterToContinue();
                }
                case 7 -> {
                    System.out.println("Select default currency:");
                    wallet.printListOfCurrencies();
                    int currency = scanner.scannerInt();
                    if (Wallet.listOfCurrencies().containsKey(currency)) {
                        op.printExchangeRates(currency);
                    } else {
                        System.err.println("That currency doesn't exist!");
                    }
                    scanner.pressEnterToContinue();
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
            Wallet.listOfCurrencies().put(choosedCurrency, operation);
            System.out.printf("%s %s %s%n", "Your account balance has been changed by", amount, Wallet.listOfCurrencies().get(choosedCurrency).getCurrency());
        } else {
            System.err.println("Amount of money must be larger than 0!");
        }
    }
}
