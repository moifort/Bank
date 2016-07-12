package com.bank.core;


import com.bank.Account;
import com.bank.BankService;
import com.bank.Currency;
import com.bank.exception.CurrencyException;
import com.bank.repository.AccountRepository;

public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;

    public BankServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account withdraw(String accountId, double moneyToWithdraw, Currency currency) {
        Account account = accountRepository.getAccount(accountId);

        double currentBalance = account.getBalanceMoney();
        Currency balanceCurrency = account.getBalanceCurrency();
        if (balanceCurrency != currency) {
            throw new CurrencyException("Account " + account.getId() +
                    " current: " + currentBalance + " " + balanceCurrency + " - " +
                    " withdraw: " + moneyToWithdraw + " " + currency);
        }

        double newBalance = currentBalance - moneyToWithdraw;
        return accountRepository.updateBalance(accountId, newBalance, Currency.EUR);
    }

}