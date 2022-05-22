package com.github.hrozhek.noizalyzerserver.service;

//todo swagger annotations

import com.github.hrozhek.noizalyzerserver.context.ApplicationContext;

public interface BaseService {

    void registerController();

    void registerData();

    void unregisterController();

    //todo methods for getting list of controllers, files etc.
}
