package com.bank.support;

import com.bank.Account;
import com.bank.Currency;
import com.bank.repository.AccountRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockAccountRepository implements AccountRepository {

    private final Map<String, MockAccount> accounts = new ConcurrentHashMap<>();

    @Override
    public Account getAccount(String id) {
        return accounts.get(id);
    }

    @Override
    public Account updateBalance(String accountId, double newBalance, Currency currency) {
        MockAccount accountToUpdate = accounts.get(accountId);
        accountToUpdate.setMoney(newBalance);
        accountToUpdate.setCurrency(currency);
        accounts.put(accountToUpdate.getId(), accountToUpdate);
        return accountToUpdate;
    }

    public Account saveNewAccount(String id, double money, Currency currency) {
        MockAccount newAccount = new MockAccount(id, money, currency);
        accounts.put(newAccount.getId(), newAccount);
        return newAccount;
    }
}
