package com.bank.core;

import com.bank.Account;
import com.bank.Client;
import com.bank.Currency;
import com.bank.exception.CurrencyException;

public class AccountImpl implements Account {
    private final String id;
    private final Client client;

    private double balance;
    private final Currency currency;

    public AccountImpl(String id,
                       Client client,
                       double initBalance,
                       Currency initCurrency) {
        this.id = id;
        this.client = client;
        this.balance = initBalance;
        this.currency = initCurrency;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public double getBalanceMoney() {
        return balance;
    }

    @Override
    public Currency getBalanceCurrency() {
        return currency;
    }

    @Override
    public void withdraw(double moneyToWithdraw, Currency withdrawCurrency) {
        if (withdrawCurrency != currency) {
            throw new CurrencyException("Account " + id +
                    " current: " + balance + " " + currency + " - " +
                    " withdraw: " + moneyToWithdraw + " " + currency);
        }
        balance = balance - moneyToWithdraw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountImpl account = (AccountImpl) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Account of " + client.getName() + " with id " + id +" has a current balance of " + balance + " " + currency.name();
    }
}
