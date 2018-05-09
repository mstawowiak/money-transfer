package com.github.mstawowiak.money.transfer.application.transaction;

import com.github.mstawowiak.money.transfer.domain.Account;
import com.github.mstawowiak.money.transfer.domain.AccountDao;
import com.github.mstawowiak.money.transfer.domain.AccountId;
import com.github.mstawowiak.money.transfer.domain.Money;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
class TransactionService {

    @Inject
    private AccountDao accountDao;

    public void processTransaction(AccountId sourceAccountId, AccountId destinationAccountId, Money money) {
        Account sourceAccount = accountDao.find(sourceAccountId);
        Account destinationAccount = accountDao.find(destinationAccountId);

        sourceAccount.withdraw(money);
        destinationAccount.deposit(money);

        accountDao.save(sourceAccount, destinationAccount);
    }
}
