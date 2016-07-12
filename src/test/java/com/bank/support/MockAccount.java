package com.bank.support;


import com.bank.Account;
import com.bank.Currency;

public class MockAccount implements Account {
    private final String id;
    private double money;
    private Currency currency;

    public MockAccount(String id, double money, Currency currency) {
        this.id = id;
        this.money = money;
        this.currency = currency;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getBalanceMoney() {
        return money;
    }

    @Override
    public Currency getBalanceCurrency() {
        return currency;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MockAccount that = (MockAccount) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id + " account has a balance of " + money + " " + currency;
    }
}
