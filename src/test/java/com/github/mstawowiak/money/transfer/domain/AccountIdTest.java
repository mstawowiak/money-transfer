package com.github.mstawowiak.money.transfer.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountIdTest {

    private static final String CORRECT_VALUE = "PL61109010140000071219812874";

    @Test
    public void shouldCreateBasketIdForCorrectValue() {
        AccountId accountId = AccountId.of(CORRECT_VALUE);

        assertEquals(CORRECT_VALUE, accountId.getAccountId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNullValue() {
        AccountId.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForEmptyValue() {
        AccountId.of("");
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForTooLongValue() {
        AccountId.of(CORRECT_VALUE + "A");
    }

}
