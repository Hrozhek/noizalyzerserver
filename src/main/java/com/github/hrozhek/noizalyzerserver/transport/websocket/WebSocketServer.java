package com.github.hrozhek.noizalyzerserver.transport.websocket;

import com.github.hrozhek.noizalyzerserver.blackmagic.ServletAwareConfig;
import com.github.hrozhek.noizalyzerserver.context.ApplicationContext;
import com.github.hrozhek.noizalyzerserver.exception.BaseException;
import com.github.hrozhek.noizalyzerserver.model.FileData;
import com.github.hrozhek.noizalyzerserver.repo.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

@ServerEndpoint(value = "/noizalyzer/{wsId}/{sensorId}", configurator = ServletAwareConfig.class)
@Component//todo check that not singleton
public class WebSocketServer {

    private ApplicationContext applicationContext;
    private FileRepo fileRepo;

    private Session session;
    private String wsId;
    private int sensorId;
    private String controllerId;

    /**
     * It is not the Spring framework who control this as dependency, so we must do some tricks to get WebSocketServer.
     * {@link Autowired} doesn't work in current configuration. Anyway Spring does have some deal with this class
     * lifetime because nothing works without {@link Component} annotation. Maybe it's just pass control to the javax
     * and tomcat after (strange) creation of it.
     * TODO need to be figured out
     */
    public WebSocketServer() {
    }

    /* todo Neither this or fields marked as @Autowired will work
    @Autowired
    public WebSocketServer(ApplicationContext context, FileRepo fileRepo) {
        this.applicationContext = context;
        this.fileRepo = fileRepo;
    }
     */

    @OnOpen
    public void connect(Session session,
                        @PathParam("wsId") String wsId, @PathParam("sensorId") int sensorId,
                        EndpointConfig config) {
        this.session = session;
        this.wsId = wsId;
        this.sensorId = sensorId;
        initContextFromConfig(config);
        controllerId = applicationContext.getControllerByWs(wsId).map(UUID::toString).orElseThrow(BaseException::new);//todo
    }

    private void initContextFromConfig(EndpointConfig config) {
        if (applicationContext != null) {
            return;
        }
        applicationContext = Optional.ofNullable((ServletContext) config.getUserProperties().get(ServletAwareConfig.SERVLET_CONTEXT_PROP))
                .map(c -> (ApplicationContext) c.getAttribute(ApplicationContext.class.getName()))
                .orElseThrow(BaseException::new);//todo
        fileRepo = applicationContext.getFileRepo();
    }

    @OnMessage
    public void processBinaryMessage(ByteBuffer buffer) {
        FileData fileData = new FileData(controllerId, sensorId);
        fileRepo.save(buffer, fileData);
    }

    @OnError
    public void processThrowable(Throwable throwable) {
        System.out.println("ws threw exception " + throwable);
        //todo
    }

    @OnClose
    public void close() {
        System.out.println("ws close method invoked");
        //todo
    }
}
