package org.example.wallet.operations;

import org.example.users.LogIn;
import org.example.wallet.Wallet;
import org.example.wallet.currencies.Currency;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.wallet.Wallet.getListOfOneUnitOfCurrency;
import static org.example.wallet.Wallet.listOfCurrencies;

public class ConvertCurrency {
    private static Map<Integer, Currency> loggedUserWallet;

    public static void setLoggedUserWallet(Map<Integer, Currency> loggedUserWallet) {
        ConvertCurrency.loggedUserWallet = loggedUserWallet;
    }

    private CurrencyConversion convertTo(MonetaryAmount currency) {
        ExchangeRateProvider rateProvider = MonetaryConversions.getExchangeRateProvider("IMF");
        return rateProvider.getCurrencyConversion(currency.getCurrency());
    }

    public void convertCurrency(int firstCur, int secondCur, BigDecimal amount) {
        if (loggedUserWallet.containsKey(firstCur) && loggedUserWallet.containsKey(secondCur)) {
            Money cur1 = loggedUserWallet.get(firstCur).currency();
            Money cur2 = loggedUserWallet.get(secondCur).currency();
            Money result = Money.of(amount, cur1.getCurrency());
            cur1 = cur1.subtract(result);
            cur2 = cur2.add(result.with(convertTo(cur2)));
            loggedUserWallet.get(firstCur).setCurrency(cur1);
            loggedUserWallet.get(secondCur).setCurrency(cur2);
        }
    }

    public void printExchangeRates(int defaultCurrency) {
        List<CurrencyConversion> conversionList = new ArrayList<>();
        Currency selectedOne = getListOfOneUnitOfCurrency().values().stream()
                .filter(currency -> currency.currency().getCurrency()
                        .equals(loggedUserWallet.get(defaultCurrency).currency().getCurrency()))
                .toList()
                .get(0);

        for (Currency cur : Wallet.listOfCurrencies().values()) {
            conversionList.add(convertTo(cur.currency()));
        }
        
        for (CurrencyConversion conversionRate : conversionList) {
            MonetaryAmount choosedCurrency = selectedOne.currency().with(conversionRate);
            System.out.printf("%s - %s: %.2f%n",
                    loggedUserWallet.get(defaultCurrency).currency().getCurrency(), choosedCurrency.getCurrency(), choosedCurrency.getNumber().doubleValue());
        }
    }

    public void getSumOfMoney(int covertToCurrency) {
        Money sum = Money.of(0, loggedUserWallet.get(covertToCurrency).currency().getCurrency());
        for (Currency m : loggedUserWallet.values()) {
            Money amountInDefaultCurrency = Money.from(m.currency().with(convertTo(sum)));
            sum = sum.add(amountInDefaultCurrency);
        }
        System.out.println("Your account balance: " + sum);
    }
}
