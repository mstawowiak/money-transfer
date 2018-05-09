package com.github.mstawowiak.money.transfer.domain;

import com.github.mstawowiak.money.transfer.domain.exception.MoneyOperationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Money {

    public static final Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");

    public static final Money ZERO = Money.of(BigDecimal.ZERO);

    private BigDecimal amount;
    private String currencyCode;

    public static Money of(int amount) {
        return of(new BigDecimal(amount));
    }

    public static Money of(BigDecimal amount) {
        return of(amount, DEFAULT_CURRENCY);
    }

    public static Money of(BigDecimal amount, Currency currency) {
        return of(amount, currency.getCurrencyCode());
    }

    public static Money of(BigDecimal amount, String currencyCode) {
        Money money = new Money();
        money.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        money.currencyCode = currencyCode;

        return money;
    }

    public Currency getCurrency() {
        return Currency.getInstance(currencyCode);
    }

    public Money multiplyBy(int multiplier) {
        return Money.of(amount.multiply(new BigDecimal(multiplier)), currencyCode);
    }

    public Money add(Money money) {
        checkCurrencyCompatibility(money);

        return Money.of(amount.add(money.amount), currencyCode);
    }

    public Money subtract(Money money) {
        checkCurrencyCompatibility(money);

        return Money.of(amount.subtract(money.amount), currencyCode);
    }

    public int compareTo(Money money) {
        checkCurrencyCompatibility(money);

        return amount.compareTo(money.amount);
    }

    private void checkCurrencyCompatibility(Money other) {
        if (!currencyCode.equals(other.currencyCode)) {
            throw new MoneyOperationException("Currency mismatch: %s != %s", currencyCode, other.currencyCode);
        }
    }

    @Override
    public String toString() {
        return String.format("%0$.2f %s", amount, getCurrency().getSymbol());
    }
}
