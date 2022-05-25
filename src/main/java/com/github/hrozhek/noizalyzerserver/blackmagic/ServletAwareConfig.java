package com.github.hrozhek.noizalyzerserver.blackmagic;

import com.github.hrozhek.noizalyzerserver.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ServletAwareConfig extends ServerEndpointConfig.Configurator {
    public static final String SERVLET_CONTEXT_PROP = "servletContext";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        ServletContext servletContext;
        if (httpSession != null && (servletContext = httpSession.getServletContext()) != null) {
            addApplicationContext(servletContext);
            config.getUserProperties().put(SERVLET_CONTEXT_PROP, servletContext);
        }
    }

    private void addApplicationContext(ServletContext servletContext) {
        if (applicationContext == null) {
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletContext);
        }
        if (springInitSuccessful() && servletContext.getAttribute(ApplicationContext.class.getName()) == null) {
            servletContext.setAttribute(ApplicationContext.class.getName(), applicationContext);
        }
    }

    private boolean springInitSuccessful() {
        return applicationContext != null;
    }
}
