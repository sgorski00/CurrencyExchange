package org.example.wallet.operations;

import org.example.wallet.Wallet;
import org.example.wallet.currencies.CurrencyTemplate;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConvertCurrency extends Wallet {
    private CurrencyConversion convertTo(MonetaryAmount currency) {
        ExchangeRateProvider rateProvider = MonetaryConversions.getExchangeRateProvider("IMF");
        return rateProvider.getCurrencyConversion(currency.getCurrency());
    }

    public void convertCurrency(int firstCur, int secondCur, BigDecimal amount) {
        if (listOfCurrencies().containsKey(firstCur) && listOfCurrencies().containsKey(secondCur)) {
            Money cur1 = listOfCurrencies().get(firstCur).currency();
            Money cur2 = listOfCurrencies().get(secondCur).currency();
            Money result = Money.of(amount, cur1.getCurrency());
            Money restOfCur1 = cur1.subtract(result);
            cur1 = cur1.subtract(restOfCur1);
            cur2 = cur2.add(cur1.with(convertTo(cur2)));
            listOfCurrencies().get(firstCur).setCurrency(cur1);
            listOfCurrencies().get(secondCur).setCurrency(cur2);
        }
    }

    public void printExchangeRates(int defaultCurrency) {
        if (listOfCurrencies().containsKey(defaultCurrency)) {
            CurrencyTemplate temp = listOfCurrencies().get(defaultCurrency);
            List<CurrencyConversion> conversionList = new ArrayList<>();
            for (CurrencyTemplate cur : listOfCurrencies().values()) {
                if (!(cur.equals(temp))) {
                    conversionList.add(convertTo(cur.currency()));
                }
            }
            if(getListOfOneUnitOfCurrency().containsKey(temp)) {
                for (CurrencyConversion cur2 : conversionList) {
                    MonetaryAmount choosedCurrency = getListOfOneUnitOfCurrency().get(temp).currency().with(cur2);
                    System.out.printf("%s - %s: %.2f%n",
                            temp.currency().getCurrency(), choosedCurrency.getCurrency(), choosedCurrency.getNumber().doubleValue());
                }
            }
        }
    }

    public void getSumOfMoney(int covertToCurrency) {
        Money sum = Money.of(0, listOfCurrencies().get(covertToCurrency).currency().getCurrency());
        for (CurrencyTemplate m : listOfCurrencies().values()) {
            Money amountInDefaultCurrency = Money.from(m.currency().with(convertTo(sum)));
            sum = sum.add(amountInDefaultCurrency);
        }
        System.out.println("Your account balance: " + sum);
    }
}
