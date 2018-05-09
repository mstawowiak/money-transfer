package com.github.mstawowiak.money.transfer.application.account;

import com.github.mstawowiak.money.transfer.domain.AccountId;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/v1/accounts")
public class AccountApi {

    @Inject
    private AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDto getAccount(@PathParam("accountId") @NotNull String accountId) {
        return accountService.getAccount(AccountId.of(accountId));
    }
}
