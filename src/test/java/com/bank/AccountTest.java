package com.bank;

import com.bank.core.AccountImpl;
import com.bank.core.ClientImpl;
import com.bank.exception.CurrencyException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    private Account account;


    @Before
    public void setUp() throws Exception {
        Client client = new ClientImpl("1", "pierre-jean");
        account = new AccountImpl("1", client, 100, Currency.EUR);
    }

    @Test
    public void testWithdraw10EURFrom100EURAccountShouldReturnAccountWith90EURBalance() throws Exception {
        // When
        account.withdraw(10, Currency.EUR);

        // Then
        assertThat(account.getBalanceMoney()).isEqualTo(90);
        assertThat(account.getBalanceCurrency()).isEqualTo(Currency.EUR);
    }

    // TODO: See with PO how to manage this case
    @Test
    public void testWithdraw110EURFrom100EURAccountShouldReturnAccountWithMinus10EURBalance() throws Exception {
        // When
        account.withdraw(110, Currency.EUR);

        // Then
        assertThat(account.getBalanceMoney()).isEqualTo(-10);
        assertThat(account.getBalanceCurrency()).isEqualTo(Currency.EUR);
    }

    // TODO: See with PO how to manage this case
    @Test
    public void testWithdraw0EURFrom100EURAccountShouldReturnAccountWith100EURBalance() throws Exception {
        // When
        account.withdraw(0, Currency.EUR);

        // Then
        assertThat(account.getBalanceMoney()).isEqualTo(100);
        assertThat(account.getBalanceCurrency()).isEqualTo(Currency.EUR);
    }

    // TODO: See with PO how to manage this case
    @Test(expected = CurrencyException.class)
    public void testWithdraw10USDFrom100EURAccountShouldReturnCurrencyException() {
        // When
        account.withdraw(0, Currency.USD);

        // Then
        // Currency exception
    }

}