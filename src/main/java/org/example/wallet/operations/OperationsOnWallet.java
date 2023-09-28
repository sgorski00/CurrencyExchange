package org.example.wallet.operations;

import org.example.Input;
import org.example.users.User;
import org.example.wallet.Wallet;
import org.example.wallet.currencies.Currency;
import org.javamoney.moneta.Money;

public class OperationsOnWallet {
    Wallet wallet = new Wallet();
    Input scanner = new Input();
    public void addCurrencyToWallet(User loggedUser){
        System.out.println("Select currency to add:");
        wallet.printListOfCurrencies();
        int i = scanner.scannerInt();

        long count = loggedUser.wallet().currencies().values().stream()
                .filter(currency -> currency.currency().getCurrency().
                        equals(Wallet.listOfCurrencies().get(i).currency().getCurrency()))
                .count();

        if(count == 0) {
            Currency cur = new Currency(Wallet.listOfCurrencies().get(i).currency().getCurrency().toString(), 0) {
                @Override
                public Money currency() {
                    return super.currency();
                }
            };
            loggedUser.wallet().currencies().put(loggedUser.wallet().currencies().size() + 1, cur);
            System.out.println("The currency added to your wallet.");
        }else{
            System.out.println("Your wallet already contains that currency.");
        }
        scanner.pressEnterToContinue();
    }

    public void removeCurrencyFromWallet(User thisUser) {
        System.out.println("Choose Currency to remove: (It must be empty!)");
        wallet.printNumericListOfUsersCurrencies();
        int choice = scanner.scannerInt();
        if(thisUser.wallet().currencies().get(choice).currency().getNumber().doubleValue() == 0) {
            thisUser.wallet().currencies().remove(choice);
            System.out.println("The currency removed form your wallet.");
        }else{
            System.err.println("The currency is not empty. Firstly remove all the money from there.");
        }
        scanner.pressEnterToContinue();
    }
}
