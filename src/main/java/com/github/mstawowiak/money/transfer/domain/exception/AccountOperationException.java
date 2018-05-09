package com.github.mstawowiak.money.transfer.domain.exception;

public class AccountOperationException extends RuntimeException {

    public AccountOperationException(String message) {
        super(message);
    }

    public AccountOperationException(String message, Object... params) {
        super(String.format(message, params));
    }
}
