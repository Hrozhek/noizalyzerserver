package com.github.hrozhek.noizalyzerserver.service;

//todo swagger annotations

import java.util.UUID;

public interface BaseService {

//    @GetMapping
    UUID registerController();

    String registerData(String controllerId);

    String registerWebSocket(String controllerId);

    void unregisterController(String id);

    //todo methods for getting list of controllers, files etc.
}
