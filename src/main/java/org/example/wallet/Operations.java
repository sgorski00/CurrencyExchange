package org.example.wallet;

import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;
import java.math.BigDecimal;

public class Operations extends Wallet {
    public Money addMoney(BigDecimal value, MonetaryAmount currency) {
        return Money.from(currency.add(Money.of(value, currency.getCurrency())));
    }

    public Money removeMoney(BigDecimal value, MonetaryAmount currency) {
        return Money.from(currency.subtract(Money.of(value, currency.getCurrency())));
    }

    public Money setMoney(BigDecimal value, MonetaryAmount currency) {
        Money cur = Money.from(currency);
        currency = currency.subtract(cur);
        currency = addMoney(value, currency);
        return Money.from(currency);
    }

    private CurrencyConversion convertTo(MonetaryAmount currency) {
        ExchangeRateProvider rateProvider = MonetaryConversions.getExchangeRateProvider("IMF");
        return rateProvider.getCurrencyConversion(currency.getCurrency());
    }

    public void getSumOfMoney(int covertToCurrency) {
        Money sum = Money.of(0, listOfCurrencies().get(covertToCurrency).getCurrency());
        for (Money m : listOfCurrencies().values()) {
            Money amountInDefaultCurrency = Money.from(m.with(convertTo(sum)));
            sum = sum.add(amountInDefaultCurrency);
        }
        System.out.println("Your account balance: " + sum);
    }

    public void convertCurrency(int firstCur, int secondCur, BigDecimal amount) {
        if (listOfCurrencies().containsKey(firstCur) && listOfCurrencies().containsKey(secondCur)) {
            Money cur1 = listOfCurrencies().get(firstCur);
            Money cur2 = listOfCurrencies().get(secondCur);
            Money restOfCur1 = removeMoney(amount, cur1);
            cur1 = cur1.subtract(restOfCur1);
            listOfCurrencies().put(firstCur, restOfCur1);
            listOfCurrencies().put(secondCur, cur2.add(cur1.with(convertTo(cur2))));
        }
    }

    public void printExchangeRates(int defaultCurrency) {
        if (listOfCurrencies().containsKey(defaultCurrency)) {
            CurrencyConversion conversionEUR = convertTo(eur());
            CurrencyConversion conversionPLN = convertTo(pln());
            CurrencyConversion conversionUSD = convertTo(usd());
            if(listOfCurrencies().get(defaultCurrency).getCurrency() == usd().getCurrency()) {
                MonetaryAmount convertedAmountUSDtoEUR = oneDollar().with(conversionEUR);
                MonetaryAmount convertedAmountUSDtoPLN = oneDollar().with(conversionPLN);
                System.out.println("USD - EUR: " + convertedAmountUSDtoEUR.getNumber());
                System.out.println("USD - PLN: " + convertedAmountUSDtoPLN.getNumber());
            }
            if(listOfCurrencies().get(defaultCurrency).getCurrency() == eur().getCurrency()) {
                MonetaryAmount convertedAmountEURtoUSD = oneEuro().with(conversionUSD);
                MonetaryAmount convertedAmountEURtoPLN = oneEuro().with(conversionPLN);
                System.out.println("EUR - USD: " + convertedAmountEURtoUSD.getNumber());
                System.out.println("EUR - PLN: " + convertedAmountEURtoPLN.getNumber());
            }
            if(listOfCurrencies().get(defaultCurrency).getCurrency() == pln().getCurrency()) {
                MonetaryAmount convertedAmountPLNtoEUR = onePln().with(conversionEUR);
                MonetaryAmount convertedAmountPLNtoUSD = onePln().with(conversionUSD);
                System.out.println("PLN - USD: " + convertedAmountPLNtoUSD.getNumber());
                System.out.println("PLN - EUR: " + convertedAmountPLNtoEUR.getNumber());
            }
        }
    }
}
