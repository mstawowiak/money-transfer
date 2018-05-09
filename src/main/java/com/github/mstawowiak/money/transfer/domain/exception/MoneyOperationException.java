package com.github.mstawowiak.money.transfer.domain.exception;

public class MoneyOperationException  extends RuntimeException {

    public MoneyOperationException(String message) {
        super(message);
    }

    public MoneyOperationException(String message, Object... params) {
        super(String.format(message, params));
    }
}