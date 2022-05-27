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

    private static final String APP_CTX = ApplicationContext.class.getName();

    @Autowired//doesn't work
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
        if (applicationContext == null || servletContext.getAttribute(APP_CTX) == null) {
            initApplicationContext(servletContext);
        }
    }

    private void initApplicationContext(ServletContext servletContext) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletContext);
        if (applicationContext == null) {
            throw new RuntimeException("init unsuccessful");//todo
        }
        servletContext.setAttribute(APP_CTX, applicationContext);
    }
}
