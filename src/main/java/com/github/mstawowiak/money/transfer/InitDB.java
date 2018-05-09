package com.github.mstawowiak.money.transfer;

import com.github.mstawowiak.money.transfer.domain.Account;
import com.github.mstawowiak.money.transfer.domain.AccountDao;
import com.github.mstawowiak.money.transfer.domain.AccountId;
import com.github.mstawowiak.money.transfer.domain.Money;
import com.github.mstawowiak.money.transfer.util.RequestContextProvider;
import java.math.BigDecimal;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class InitDB {

    @Inject
    private AccountDao dao;

    @RequestContextProvider
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        Account a1 = Account.builder()
                .accountId(AccountId.of("IT100EUR"))
                .balance(Money.of(100))
                .build();

        Account a2 = Account.builder()
                .accountId(AccountId.of("IT200EUR"))
                .balance(Money.of(200))
                .build();

        Account a3 = Account.builder()
                .accountId(AccountId.of("IT300EUR"))
                .balance(Money.of(300))
                .build();

        Account a4 = Account.builder()
                .accountId(AccountId.of("IT400EUR"))
                .balance(Money.of(400))
                .build();

        Account a5 = Account.builder()
                .accountId(AccountId.of("IT500EUR"))
                .balance(Money.of(500))
                .build();

        Account a6 = Account.builder()
                .accountId(AccountId.of("IT600EUR"))
                .balance(Money.of(600))
                .build();

        Account a7 = Account.builder()
                .accountId(AccountId.of("PL700PLN"))
                .balance(Money.of(new BigDecimal(700), "PLN"))
                .build();

        Account a8 = Account.builder()
                .accountId(AccountId.of("PL800PLN"))
                .balance(Money.of(new BigDecimal(800), "PLN"))
                .build();

        Account a9 = Account.builder()
                .accountId(AccountId.of("IT900EUR"))
                .balance(Money.of(900))
                .build();

        Account a10 = Account.builder()
                .accountId(AccountId.of("IT1000EUR"))
                .balance(Money.of(1000))
                .build();

        dao.save(a1, a2, a3, a4, a5, a6, a7, a8 ,a9, a10);
    }

}
