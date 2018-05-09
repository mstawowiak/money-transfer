package com.github.mstawowiak.money.transfer.infrastructure.jpa;

import com.github.mstawowiak.money.transfer.domain.AccountId;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountIdJpaConverter implements AttributeConverter<AccountId, String> {

    @Override
    public String convertToDatabaseColumn(AccountId accountId) {
        return accountId.getAccountId();
    }

    @Override
    public AccountId convertToEntityAttribute(String accountId) {
        return AccountId.of(accountId);
    }
}
