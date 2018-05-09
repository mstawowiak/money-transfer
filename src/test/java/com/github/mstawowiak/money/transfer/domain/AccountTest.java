package com.github.mstawowiak.money.transfer.domain;

import com.github.mstawowiak.money.transfer.domain.exception.AccountOperationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private static final AccountId ACCOUNT_ID = AccountId.of("PL61109010140000071219812874");
    private static final Money START_BALANCE = Money.of(100);

    @Test
    public void shouldCorrectDeposit() {
        Account account = Account.builder()
                .accountId(ACCOUNT_ID)
                .balance(START_BALANCE)
                .build();

        account.deposit(Money.of(150));

        assertEquals(Money.of(250), account.getBalance());
    }

    @Test
    public void shouldCorrectWithdraw() {
        Account account = Account.builder()
                .accountId(ACCOUNT_ID)
                .balance(START_BALANCE)
                .build();

        account.withdraw(Money.of(40));

        assertEquals(Money.of(60), account.getBalance());
    }

    @Test
    public void shouldCorrectWithdrawAllMoney() {
        Account account = Account.builder()
                .accountId(ACCOUNT_ID)
                .balance(START_BALANCE)
                .build();

        account.withdraw(START_BALANCE);

        assertEquals(Money.of(0), account.getBalance());
    }

    @Test(expected = AccountOperationException.class)
    public void shouldThrowExceptionWhenWithdrawMoreThanBalance() {
        Account account = Account.builder()
                .accountId(ACCOUNT_ID)
                .balance(START_BALANCE)
                .build();

        account.withdraw(Money.of(101));
    }

}
