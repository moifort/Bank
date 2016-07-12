package com.bank.step;

import com.bank.Account;
import com.bank.BankService;
import com.bank.Currency;
import com.bank.core.BankServiceImpl;
import com.bank.support.MockAccountRepository;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;


public class WithdrawSteps {

    private BankService bankService;
    private MockAccountRepository accountRepository;
    private Account currentAccount;

    @Before
    public void setUp() throws Exception {
        accountRepository = new MockAccountRepository();
        bankService = new BankServiceImpl(accountRepository);
    }

    @Given("^an existing client named \"(.*)\" with (\\d+\\.\\d+) (.*) in his account$")
    public void initAccount(String userName, double totalMoney, Currency currency) throws Throwable {
        currentAccount = accountRepository.saveNewAccount(userName, totalMoney, currency);
    }

    @When("^he withdraws (\\d+\\.\\d+) (.*) from his account$")
    public void clientWithdraws(double moneyWithdraw, Currency currency) throws Throwable {
        currentAccount = bankService.withdraw(currentAccount.getId(), moneyWithdraw, currency);
    }

    @Then("^the new balance is (\\d+\\.\\d+) (.*)$")
    public void verifyNewBalance(double balanceToVerify, Currency currency) throws Throwable {
        assertThat(currentAccount.getBalanceMoney()).isEqualTo(balanceToVerify);
        assertThat(currentAccount.getBalanceCurrency()).isEqualTo(currency);
    }
}
