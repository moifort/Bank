package com.bank.repository;

import com.bank.Account;
import com.bank.Currency;

public interface AccountRepository {
    Account getAccount(String id);
    Account updateBalance(String accountId, double newBalance, Currency currency);
}
