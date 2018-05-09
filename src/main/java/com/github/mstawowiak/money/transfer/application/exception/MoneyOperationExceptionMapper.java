package com.github.mstawowiak.money.transfer.application.exception;

import com.github.mstawowiak.money.transfer.domain.exception.MoneyOperationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MoneyOperationExceptionMapper implements ExceptionMapper<MoneyOperationException> {

    @Override
    public Response toResponse(MoneyOperationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Money transfer error: " + exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}