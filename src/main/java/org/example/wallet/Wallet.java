package org.example.wallet;

import org.javamoney.moneta.Money;

import java.util.HashMap;
import java.util.Map;

public class Wallet extends Currencies {
    private static Map<Integer, Money> listOfCurrencies = new HashMap<>();
    private static final Money usd = usdAmount();
    private static final Money eur = eurAmount();
    private static final Money pln = plnAmount();

//    String walletName;
//    int walletPin;
//    Map<walletname, money>

    static {
        listOfCurrencies.put(1, pln());
        listOfCurrencies.put(2, usd());
        listOfCurrencies.put(3, eur());
    }

    public void printListOfCurrencies(){
        for(int i = 1; i<=listOfCurrencies.size(); i++){
            System.out.printf("%d. %s%n", i, listOfCurrencies.get(i).getCurrency());
        }
    }

    public void printListOfAmmountOfCurrencies(){
        for(int i = 1; i<=listOfCurrencies.size(); i++){
            System.out.printf("%d. %s%n", i, listOfCurrencies.get(i));
        }
    }

    public static Money usd() {
        return usd;
    }

    public static Money eur() {
        return eur;
    }

    public static Money pln() {
        return pln;
    }

    public static Map<Integer, Money> listOfCurrencies() {
        return listOfCurrencies;
    }

    public static void setListOfCurrencies(Map<Integer, Money> listOfCurrencies) {
        Wallet.listOfCurrencies = listOfCurrencies;
    }
}
