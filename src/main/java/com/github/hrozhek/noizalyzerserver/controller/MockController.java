package com.github.hrozhek.noizalyzerserver.controller;

import com.github.hrozhek.noizalyzerserver.service.BaseService;
import org.springframework.web.bind.annotation.RestController;

@RestController //TODO probably it will transfer raw data instead of jsons
public class MockController {

    private final BaseService service;

    public MockController(BaseService service) {
        this.service = service;
    }
}
