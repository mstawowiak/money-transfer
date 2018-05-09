package com.github.mstawowiak.money.transfer.domain;

import com.github.mstawowiak.money.transfer.domain.exception.AccountOperationException;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    @Id
    private AccountId accountId;

    private Money balance;

    public void withdraw(Money amount) {
        if (balance.compareTo(amount) < 0) {
            throw new AccountOperationException("Cannot withdraw more money than actual balance");
        }

        this.balance = balance.subtract(amount);
    }

    public void deposit(Money amount) {
        this.balance = balance.add(amount);
    }

}
