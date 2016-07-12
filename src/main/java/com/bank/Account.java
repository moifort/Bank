package com.bank;

public interface Account {
    String getId();
    Client getClient();

    double getBalanceMoney();
    Currency getBalanceCurrency();

    void withdraw(double moneyToWithdraw, Currency currency);
}
