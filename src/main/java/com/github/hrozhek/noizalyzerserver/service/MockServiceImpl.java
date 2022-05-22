package com.github.hrozhek.noizalyzerserver.service;

import com.github.hrozhek.noizalyzerserver.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockServiceImpl implements BaseService {

    private final ApplicationContext context;

    @Autowired
    public MockServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void registerController() {

    }

//    todo not here but is some class

    @Override
    public void registerData() {
        //todo write to filesystem
    }

    @Override
    public void unregisterController() {

    }
}
