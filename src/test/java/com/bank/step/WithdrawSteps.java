package com.bank.step;

import com.bank.Account;
import com.bank.Client;
import com.bank.Currency;
import com.bank.core.AccountImpl;
import com.bank.core.ClientImpl;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;


public class WithdrawSteps {

    private Account currentAccount;

    @Given("^an existing client named \"(.*)\" with (\\d+\\.\\d+) (.*) in his account$")
    public void initAccount(String userName, double totalMoney, Currency currency) throws Throwable {
        Client client = new ClientImpl("1", userName);
        currentAccount = new AccountImpl("1", client, totalMoney, currency);
    }

    @When("^he withdraws (\\d+\\.\\d+) (.*) from his account$")
    public void clientWithdraws(double moneyWithdraw, Currency currency) throws Throwable {
        currentAccount.withdraw(moneyWithdraw, currency);
    }

    @Then("^the new balance is (\\d+\\.\\d+) (.*)$")
    public void verifyNewBalance(double balanceToVerify, Currency currency) throws Throwable {
        assertThat(currentAccount.getBalanceMoney()).isEqualTo(balanceToVerify);
        assertThat(currentAccount.getBalanceCurrency()).isEqualTo(currency);
    }
}
