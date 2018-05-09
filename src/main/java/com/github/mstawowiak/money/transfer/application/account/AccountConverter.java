package com.github.mstawowiak.money.transfer.application.account;

import com.github.mstawowiak.money.transfer.domain.Account;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class AccountConverter {

    public AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.accountId = account.getAccountId().getAccountId();
        accountDto.balance = account.getBalance().getAmount();
        accountDto.currencyCode = account.getBalance().getCurrencyCode();

        return accountDto;
    }

}
