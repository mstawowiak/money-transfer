package com.github.mstawowiak.money.transfer.application.transaction;

import com.github.mstawowiak.money.transfer.domain.AccountId;
import com.github.mstawowiak.money.transfer.domain.Money;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/v1/transaction")
public class TransactionApi {

    @Inject
    private TransactionService transactionService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void processTransaction(TransactionRequest transactionRequest) {
        AccountId sourceAccountId = AccountId.of(transactionRequest.sourceAccountId);
        AccountId destinationeAccountId = AccountId.of(transactionRequest.destinationAccountId);

        Money money = Money.of(transactionRequest.amount, transactionRequest.currencyCode);

        transactionService.processTransaction(sourceAccountId, destinationeAccountId, money);
    }

}
