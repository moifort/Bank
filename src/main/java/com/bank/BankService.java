package com.bank;

public interface BankService {

    Account withdraw(String accountId, double moneyToWithdraw, Currency currency);
}
