package com.github.hrozhek.noizalyzerserver.context;

import com.github.hrozhek.noizalyzerserver.exception.BaseException;
import com.github.hrozhek.noizalyzerserver.model.FileData;
import com.github.hrozhek.noizalyzerserver.repo.ControllerRepo;
import com.github.hrozhek.noizalyzerserver.repo.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ApplicationContext {

    private final ConcurrentMap<String, UUID> webSocketToControllerIds = new ConcurrentHashMap<>();
    private final FileRepo fileRepo;
    private final ControllerRepo controllerRepo;

    @Autowired
    public ApplicationContext(FileRepo fileRepo, ControllerRepo controllerRepo) {
        this.fileRepo = fileRepo;
        this.controllerRepo = controllerRepo;
    }
//    private final ConcurrentMap<String, FileData> webSocketToControllerData = new ConcurrentHashMap<>();

    public Optional<UUID> getControllerByWs(String ws) {
        return Optional.ofNullable(webSocketToControllerIds.get(ws));
    }
    public void bindWsToController(String ws, UUID controllerId) {
        webSocketToControllerIds.put(ws, controllerId);
    }

    public void unbindWs(String ws) {
        webSocketToControllerIds.remove(ws);
    }

    public void unbindWs(UUID controllerId) {
        webSocketToControllerIds.entrySet().stream()
                .filter(e -> e.getValue().equals(controllerId))
                .findFirst()
                .ifPresent(e -> webSocketToControllerIds.remove(e.getKey()));
    }

//    public void addFileData(String webSocketId, FileData fileData) {
//        //webSocketToControllerData.putIfAbsent(webSocketId, fileData); //TODO consider this
//        webSocketToControllerData.compute(webSocketId, (k, d) -> {
//            if (webSocketToControllerData.get(k) != null) {
//                throw new BaseException();//todo
//            }
//            return fileData;
//        });
//    }

//    public void deleteFileData(FileData fileData) {
//        Map.Entry<String, FileData> entry = webSocketToControllerData.entrySet().stream()
//                .filter(e -> e.getValue().equals(fileData))
//                .findFirst()
//                .orElseThrow(BaseException::new);//todo
//        webSocketToControllerData.remove(entry.getKey());
//    }
//
//    public Optional<FileData> getFileData(String webSocketId) {
//        return Optional.ofNullable(webSocketToControllerData.get(webSocketId));
//    }


    public FileRepo getFileRepo() {
        return fileRepo;
    }

    public ControllerRepo getControllerRepo() {
        return controllerRepo;
    }
}
