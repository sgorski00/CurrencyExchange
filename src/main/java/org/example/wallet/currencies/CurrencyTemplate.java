package org.example.wallet.currencies;

import org.javamoney.moneta.Money;

import javax.money.*;

public abstract class CurrencyTemplate {
    private Money currency;

    public CurrencyTemplate(String name, long amount){
        CurrencyQuery query = CurrencyQueryBuilder.of().setCurrencyCodes(name).build();
        CurrencyUnit cu = Monetary.getCurrency(query);
        this.currency = Money.of(amount, cu);
    }

    public Money currency() {
        return currency;
    }

    public void setCurrency(Money currency) {
        this.currency = currency;
    }
}
