package org.example.wallet;

import org.example.wallet.currencies.*;
import org.javamoney.moneta.Money;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
    public static EUR eur = new EUR(0);
    public static PLN pln = new PLN(0);
    public static USD usd = new USD(0);
    public static GBP gbp = new GBP(0);

    protected static final EUR ONE_EURO = new EUR(1);
    protected static final PLN ONE_PLN = new PLN(1);
    protected static final USD ONE_USD = new USD(1);
    protected static final GBP ONE_GBP = new GBP(1);

    private static final Map<Integer, CurrencyTemplate> listOfCurrencies = new HashMap<>();
    private static final Map<CurrencyTemplate, CurrencyTemplate> listOfOneUnitOfCurrency = new HashMap<>();

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

    public void printListOfAmmountOfCurrencies(){
        for(int i = 1; i<=listOfCurrencies.size(); i++){
            System.out.printf("%d. %s%n", i, listOfCurrencies.get(i).currency());
        }
    }

    public static Map<Integer, CurrencyTemplate> listOfCurrencies() {
        return listOfCurrencies;
    }

    public static Map<CurrencyTemplate, CurrencyTemplate> getListOfOneUnitOfCurrency(){
        return listOfOneUnitOfCurrency;
    }
}
