package org.example.wallet;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

import javax.money.*;

public class Currencies {
    private static MonetaryAmount createCurrency(String currencyCode){
        CurrencyQuery query = CurrencyQueryBuilder.of().setCurrencyCodes(currencyCode).build();
        CurrencyUnit cu = Monetary.getCurrency(query);
        return FastMoney.of(0, cu);
    }

    private static final Money usdAmount = Money.from(createCurrency("USD"));
    private static final Money eurAmount = Money.from(createCurrency("EUR"));
    private static final Money plnAmount = Money.from(createCurrency("PLN"));

    private static final Money oneDollar = usdAmount().add(Money.of(1, usdAmount().getCurrency()));
    private static final Money oneEuro = eurAmount().add(Money.of(1, eurAmount().getCurrency()));
    private static final Money onePln = plnAmount().add(Money.of(1, plnAmount().getCurrency()));

    protected static Money usdAmount() {
        return usdAmount;
    }

    protected static Money eurAmount() {
        return eurAmount;
    }

    protected static Money plnAmount() {
        return plnAmount;
    }

    public static Money oneDollar() {
        return oneDollar;
    }

    public static Money oneEuro() {
        return oneEuro;
    }

    public static Money onePln() {
        return onePln;
    }
}
