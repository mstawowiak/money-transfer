package com.github.mstawowiak.money.transfer.util;

import java.util.HashMap;
import javax.enterprise.inject.spi.CDI;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.jboss.weld.context.bound.BoundRequestContext;

@Interceptor
@RequestContextProvider
public class RequestContextInterceptor {

    @AroundInvoke
    public Object attachRequestContext(InvocationContext context) throws Exception {
        BoundRequestContext requestContext = CDI.current().select(BoundRequestContext.class).get();

        requestContext.associate(new HashMap<>());
        requestContext.activate();

        Object result = context.proceed();

        try {
            requestContext.invalidate();
            requestContext.deactivate();
        } finally {
            requestContext.dissociate(new HashMap<>());
        }

        return result;
    }
}
