package com.github.mstawowiak.money.transfer.application;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/*")
public class JerseyApp extends ResourceConfig {

    public JerseyApp() {
        packages("com.github.mstawowiak.money.transfer.application.account");
        packages("com.github.mstawowiak.money.transfer.application.transaction");

        packages("com.github.mstawowiak.money.transfer.application.exception");
    }
}