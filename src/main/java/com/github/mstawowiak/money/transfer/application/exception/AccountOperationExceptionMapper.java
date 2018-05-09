package com.github.mstawowiak.money.transfer.application.exception;

import com.github.mstawowiak.money.transfer.domain.exception.AccountOperationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountOperationExceptionMapper implements ExceptionMapper<AccountOperationException> {

    @Override
    public Response toResponse(AccountOperationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Money transfer error: " + exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}