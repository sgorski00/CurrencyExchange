package org.example.wallet.operations;

import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

public class BasicOperations {
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
}
