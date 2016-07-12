package com.bank;


import com.bank.core.BankServiceImpl;
import com.bank.exception.CurrencyException;
import com.bank.support.MockAccountRepository;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankServiceTest {

    private BankService bankService;
    private MockAccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        accountRepository = new MockAccountRepository();
        bankService = new BankServiceImpl(accountRepository);
    }

    @Test
    public void testWithdraw10EURFrom100EURAccountShouldReturnAccountWith90EURBalance() throws Exception {
        // Given
        Account account = accountRepository.saveNewAccount("Thibaut", 100, Currency.EUR);

        // When
        bankService.withdraw(account.getId(), 10, Currency.EUR);

        // Then
        assertThat(account.getBalanceMoney()).isEqualTo(90);
        assertThat(account.getBalanceCurrency()).isEqualTo(Currency.EUR);
    }

    // TODO: See with PO how to manage this case
    @Test
    public void testWithdraw110EURFrom100EURAccountShouldReturnAccountWithMinus10EURBalance() throws Exception {
        // Given
        Account account = accountRepository.saveNewAccount("Thibaut", 100, Currency.EUR);

        // When
        bankService.withdraw(account.getId(), 110, Currency.EUR);

        // Then
        assertThat(account.getBalanceMoney()).isEqualTo(-10);
        assertThat(account.getBalanceCurrency()).isEqualTo(Currency.EUR);
    }

    // TODO: See with PO how to manage this case
    @Test
    public void testWithdraw0EURFrom100EURAccountShouldReturnAccountWith100EURBalance() throws Exception {
        // Given
        Account account = accountRepository.saveNewAccount("Thibaut", 100, Currency.EUR);

        // When
        bankService.withdraw(account.getId(), 0, Currency.EUR);

        // Then
        assertThat(account.getBalanceMoney()).isEqualTo(100);
        assertThat(account.getBalanceCurrency()).isEqualTo(Currency.EUR);
    }

    // TODO: See with PO how to manage this case
    @Test(expected = CurrencyException.class)
    public void testWithdraw10USDFrom100EURAccountShouldReturnCurrencyException() {
        // Given
        Account account = accountRepository.saveNewAccount("Thibaut", 100, Currency.EUR);

        // When
        bankService.withdraw(account.getId(), 0, Currency.USD);

        // Then
        // Currency exception
    }
}