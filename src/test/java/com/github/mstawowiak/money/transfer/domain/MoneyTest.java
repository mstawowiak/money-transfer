package com.github.mstawowiak.money.transfer.domain;

import com.github.mstawowiak.money.transfer.domain.exception.MoneyOperationException;
import java.math.BigDecimal;
import java.util.Currency;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoneyTest {

    private static final String EUR_CODE = "EUR";
    private static final Currency EUR = Currency.getInstance(EUR_CODE);

    private static final String AMOUNT_AS_STRING = "99.99";
    private static final BigDecimal AMOUNT = new BigDecimal(AMOUNT_AS_STRING);

    @Test
    public void shouldCreateMoneyFromString() {
        Money money = Money.of(new BigDecimal(AMOUNT_AS_STRING));

        assertEquals(AMOUNT, money.getAmount());
        assertEquals(EUR, money.getCurrency());
        assertEquals(EUR_CODE, money.getCurrencyCode());
    }

    @Test
    public void shouldCreateMoneyFromBigDecimal() {
        Money money = Money.of(AMOUNT);

        assertEquals(AMOUNT, money.getAmount());
        assertEquals(EUR, money.getCurrency());
        assertEquals(EUR_CODE, money.getCurrencyCode());
    }

    @Test
    public void shouldCreateMoneyFromBigDecimalWithCurrency() {
        Money money = Money.of(AMOUNT, EUR);

        assertEquals(AMOUNT, money.getAmount());
        assertEquals(EUR, money.getCurrency());
        assertEquals(EUR_CODE, money.getCurrencyCode());
    }

    @Test
    public void shouldCreateMoneyFromBigDecimalWithCurrencyCode() {
        Money money = Money.of(AMOUNT, EUR_CODE);

        assertEquals(AMOUNT, money.getAmount());
        assertEquals(EUR, money.getCurrency());
        assertEquals(EUR_CODE, money.getCurrencyCode());
    }

    @Test
    public void shouldCorrectMultiply() {
        Money money = Money.of(AMOUNT);

        Money result = money.multiplyBy(2);

        assertEquals(new BigDecimal("199.98"), result.getAmount());
    }

    @Test
    public void shouldCorrectAdd() {
        Money money = Money.of(AMOUNT);
        Money other = Money.of(BigDecimal.TEN);

        Money result = money.add(other).add(other);

        assertEquals(new BigDecimal("119.99"), result.getAmount());
    }

    @Test
    public void shouldCorrectSubtract() {
        Money money = Money.of(AMOUNT);
        Money other = Money.of(BigDecimal.TEN);

        Money result = money.subtract(other).subtract(other);

        assertEquals(new BigDecimal("79.99"), result.getAmount());
    }

    @Test(expected = MoneyOperationException.class)
    public void shouldThrowExceptionWhenTryAddDifferentCurrencies() {
        Money money = Money.of(AMOUNT);
        Money other = Money.of(BigDecimal.TEN, "USD");

        money.add(other);
    }

}
