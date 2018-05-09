package com.github.mstawowiak.money.transfer.domain;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();

    Account find(AccountId accountId);

    void save(Account... accounts);

}
