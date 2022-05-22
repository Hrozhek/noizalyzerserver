package com.github.hrozhek.noizalyzerserver.transport.websocket;

import com.github.hrozhek.noizalyzerserver.context.ApplicationContext;
import com.github.hrozhek.noizalyzerserver.exception.BaseException;
import com.github.hrozhek.noizalyzerserver.model.FileData;
import com.github.hrozhek.noizalyzerserver.repo.ControllerRepo;
import com.github.hrozhek.noizalyzerserver.repo.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.UUID;

@ServerEndpoint(value = "/{wsId}/{sensorId}")//todo
public class WebSocketServer {

    private final ApplicationContext applicationContext;
    private final FileRepo fileRepo;

    private Session session;
    private String wsId;
    private int sensorId;
    private String controllerId;

    @Autowired
    public WebSocketServer(ApplicationContext context, FileRepo fileRepo) {
        this.applicationContext = context;
        this.fileRepo = fileRepo;
    }

    @OnOpen
    public void connect(Session session, @PathParam("wsId") String wsId, @PathParam("sensorId") int sensorId, EndpointConfig config) {//todo maybe don't need it
        this.session = session;
        this.wsId = wsId;
        this.sensorId = sensorId;
        controllerId = applicationContext.getControllerByWs(wsId).map(UUID::toString).orElseThrow(BaseException::new);//todo
    }

    @OnMessage
    public void processBinaryMessage(ByteBuffer buffer) {
        FileData fileData = new FileData(controllerId, sensorId);
        fileRepo.save(buffer, fileData);
    }

    @OnError
    public void processThrowable(Throwable throwable) {
        //todo
    }

    @OnClose
    public void close() {
        //todo
    }
}
