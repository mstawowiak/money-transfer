package com.github.mstawowiak.money.transfer.domain;

import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

@Value
public final class AccountId {

    public static final Integer MAX_LENGTH = 28;

    private final String accountId;

    private AccountId(String accountId) {
        checkArgument(!isNullOrEmpty(accountId), "accountId cannot be null or empty");
        checkArgument(accountId.length() <= MAX_LENGTH,
                String.format("accountId must be <= %d but is %s", MAX_LENGTH, accountId));

        this.accountId = accountId;
    }

    public static AccountId of(String accountId) {
        return new AccountId(accountId);
    }
}