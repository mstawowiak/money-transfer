package com.github.mstawowiak.money.transfer;

import com.github.mstawowiak.money.transfer.application.JerseyApp;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import javax.servlet.ServletException;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.servlet.Listener;

import static io.undertow.servlet.Servlets.listener;
import static io.undertow.servlet.Servlets.servlet;

public final class Application {

    private static final String APP_NAME = "money-transfer";
    private static final String CONTEXT_PATH = "/" + APP_NAME;

    private static final String HOST = "localhost";
    private static final int PORT = 9090;

    private static Undertow server;

    public static void main(String[] args) throws ServletException {
        startContainer(HOST, PORT);
    }

    public static void stopContainer() {
        server.stop();
    }

    public static void startContainer(String host, int port) throws ServletException {
        DeploymentInfo servletBuilder = Servlets.deployment();

        servletBuilder
                .setClassLoader(Thread.currentThread().getContextClassLoader())
                .setContextPath(CONTEXT_PATH)
                .setDeploymentName(APP_NAME + ".war")
                .addListeners(listener(Listener.class))
                .addServlets(servlet("jerseyServlet", ServletContainer.class)
                        .setLoadOnStartup(1)
                        .addInitParam("javax.ws.rs.Application", JerseyApp.class.getName())
                        .addMapping("/api/*"));

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();

        PathHandler path = Handlers.path(Handlers.redirect(CONTEXT_PATH))
                .addPrefixPath(CONTEXT_PATH, manager.start());

        server = Undertow.builder()
                .addHttpListener(port, host)
                .setHandler(path)
                .build();

        server.start();
    }

    private Application() {
    }
}
