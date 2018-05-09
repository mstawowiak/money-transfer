package com.github.mstawowiak.money.transfer.infrastructure.jpa;

import com.github.mstawowiak.money.transfer.domain.Account;
import com.github.mstawowiak.money.transfer.domain.AccountDao;
import com.github.mstawowiak.money.transfer.domain.AccountId;
import com.github.mstawowiak.money.transfer.infrastructure.jpa.common.GenericDao;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountJpaDao extends GenericDao<Account> implements AccountDao {

    @Override
    public List<Account> findAll() {
        return findAll(Account.class);
    }

    @Override
    public Account find(AccountId accountId) {
        return find(Account.class, accountId);
    }

    @Override
    public void save(Account... accounts) {
        merge(Arrays.asList(accounts));
    }

}
