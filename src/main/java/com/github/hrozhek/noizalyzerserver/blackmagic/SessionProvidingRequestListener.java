package com.github.hrozhek.noizalyzerserver.blackmagic;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
@Component//interesting fact that it is not called without this Spring annotation. Well...
public class SessionProvidingRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        //ensure that session is created during handshake (using 'true' flag) when using Tomcat,
        //because it is guaranteed only by Java EE-compliant servlet containers.
        ((HttpServletRequest) event.getServletRequest()).getSession(true);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        //Though it's not needed in some implementations
        //we need this to avoid failing when tomcat's interface requestDestroyed method is called
    }

}
