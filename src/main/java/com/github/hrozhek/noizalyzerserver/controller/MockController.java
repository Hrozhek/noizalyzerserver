package com.github.hrozhek.noizalyzerserver.controller;

import com.github.hrozhek.noizalyzerserver.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController//TODO probably it will transfer raw data instead of jsons
@RequestMapping("noizalyzer")
public class MockController {

    private final BaseService service;

    public MockController(BaseService service) {
        this.service = service;
    }

    @PostMapping("/controller")
    public ResponseEntity<UUID> registerController() {
        UUID id = service.registerController();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("controller/{id}/file")
    public ResponseEntity<String> registerData(@PathVariable String controllerId) {
        return new ResponseEntity<>(service.registerWebSocket(controllerId), HttpStatus.OK);
    }

    @DeleteMapping("/controller/{id}")
    public ResponseEntity unregisterController(@PathVariable String id) {
        service.unregisterController(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
