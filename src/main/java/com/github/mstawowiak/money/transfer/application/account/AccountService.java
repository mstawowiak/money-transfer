package com.github.mstawowiak.money.transfer.application.account;

import com.github.mstawowiak.money.transfer.domain.Account;
import com.github.mstawowiak.money.transfer.domain.AccountDao;
import com.github.mstawowiak.money.transfer.domain.AccountId;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
class AccountService {

    @Inject
    private AccountDao accountDao;

    @Inject
    private AccountConverter accountConverter;

    public List<AccountDto> getAllAccounts() {
        return accountDao.findAll().stream()
                .map(account -> accountConverter.toDto(account))
                .collect(Collectors.toList());
    }

    public AccountDto getAccount(AccountId accountId) {
        Account account = accountDao.find(accountId);

        return accountConverter.toDto(account);
    }

}
