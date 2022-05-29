package com.github.hrozhek.noizalyzerserver.service;

import com.github.hrozhek.noizalyzerserver.context.ApplicationContext;
import com.github.hrozhek.noizalyzerserver.exception.BaseException;
import com.github.hrozhek.noizalyzerserver.model.StoreableControllerModel;
import com.github.hrozhek.noizalyzerserver.repo.ControllerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.UUID;

@Service
public class MockServiceImpl implements BaseService {

    private final ApplicationContext context;
    private final ControllerRepo<StoreableControllerModel> controllerRepo;

    @Autowired
    public MockServiceImpl(ApplicationContext context, ControllerRepo controllerRepo) {
        this.context = context;
        this.controllerRepo = controllerRepo;
    }

    @Override
    public UUID registerController() {//todo params for identification
        return controllerRepo.add(new StoreableControllerModel());//todo log info about controller
    }

    @Override
    public String registerData(String controllerId) {//todo tcp socket
        return "hahah";
        //todo write to filesystem TCP
    }

    @Override
    public String registerWebSocket(String controllerId) {
        if (controllerRepo.get(UUID.fromString(controllerId)) == null) {
            throw new BaseException();//todo
        }
        System.out.println("register ws: we are here, controllerId");
        String ws = UUID.randomUUID().toString();
        context.bindWsToController(ws, UUID.fromString(controllerId));
        return ws;
    }

    @Override
    @DeleteMapping("noizalyzer/controller")
    public void unregisterController(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            throw new BaseException();//todo
        }
        controllerRepo.remove(uuid);//todo 404 if not found
    }
}
