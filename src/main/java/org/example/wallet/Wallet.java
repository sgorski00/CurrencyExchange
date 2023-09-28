package org.example.wallet;

import org.example.users.LogIn;
import org.example.wallet.currencies.*;
import org.example.wallet.currencies.Currency;

import java.util.*;

public class Wallet {
    Map<Integer, Currency> currencies = new HashMap<>();
    public Wallet(Currency... cur){
        for(int i = 1; i<=cur.length; i++) {
            currencies.put(i, cur[i-1]);
        }
    }

    public Map<Integer, Currency> currencies() {
        return currencies;
    }

    public void printListOfUsersCurrencies(){
        LogIn.loggedUser().wallet().currencies().values().stream()
                .map(Currency::currency)
                .forEach(System.out::println);
    }

    public void printNumericListOfUsersCurrencies(){
        for(int i = 1; i<=LogIn.loggedUser().wallet().currencies().size(); i++){
            System.out.println(i + ". " + LogIn.loggedUser().wallet().currencies().get(i).currency().getCurrency());
        }
    }

    public static EUR eur = new EUR(0);
    public static PLN pln = new PLN(0);
    public static USD usd = new USD(0);
    public static GBP gbp = new GBP(0);

    protected static final EUR ONE_EURO = new EUR(1);
    protected static final PLN ONE_PLN = new PLN(1);
    protected static final USD ONE_USD = new USD(1);
    protected static final GBP ONE_GBP = new GBP(1);

    private static final Map<Integer, Currency> listOfCurrencies = new HashMap<>();
    private static final Map<Currency, Currency> listOfOneUnitOfCurrency = new HashMap<>();

    static {
        listOfCurrencies.put(1, pln);
        listOfCurrencies.put(2, usd);
        listOfCurrencies.put(3, eur);
        listOfCurrencies.put(4, gbp);

        listOfOneUnitOfCurrency.put(eur, ONE_EURO);
        listOfOneUnitOfCurrency.put(gbp, ONE_GBP);
        listOfOneUnitOfCurrency.put(pln, ONE_PLN);
        listOfOneUnitOfCurrency.put(usd, ONE_USD);
    }

    public void printListOfCurrencies(){
        for(int i = 1; i<=listOfCurrencies.size(); i++){
            System.out.printf("%d. %s%n", i, listOfCurrencies.get(i).currency().getCurrency());
        }
    }

    public static Map<Integer, Currency> listOfCurrencies() {
        return listOfCurrencies;
    }

    public static Map<Currency, Currency> getListOfOneUnitOfCurrency(){
        return listOfOneUnitOfCurrency;
    }
}
