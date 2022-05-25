package com.github.hrozhek.noizalyzerserver;

import com.github.hrozhek.noizalyzerserver.config.DirectoryConfig;
import com.github.hrozhek.noizalyzerserver.context.ApplicationContext;
import com.github.hrozhek.noizalyzerserver.repo.ControllerRepo;
import com.github.hrozhek.noizalyzerserver.repo.FileRepo;
import com.github.hrozhek.noizalyzerserver.repo.FileSystemFileRepoImpl;
import com.github.hrozhek.noizalyzerserver.repo.InMemoryControllerRepoImpl;
import com.github.hrozhek.noizalyzerserver.transport.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class MockserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockserverApplication.class, args);
    }

    @Bean
    public DirectoryConfig getDirectoryConfig() {
        try {
            Path root = Paths.get("C:\\Projects\\noize");
            return new DirectoryConfig(root, "record");
        } catch (Exception e) {
            throw new RuntimeException(e);//todo
        }
    }

    @Autowired
    @Bean
    public FileRepo getFileRepo(DirectoryConfig directoryConfig) {
        return new FileSystemFileRepoImpl(directoryConfig);
    }

    @Bean
    public ControllerRepo getControllerRepo() {
        return new InMemoryControllerRepoImpl();
    }

    @Autowired
    @Bean
    public ApplicationContext getApplicationContext(FileRepo fileRepo, ControllerRepo controllerRepo) {
        controllerRepo.getAllIds();
        return new ApplicationContext(fileRepo, controllerRepo);
    }

    @Configuration
    @EnableWebSocket
    protected static class WsConfig {
        @Bean
        public ServerEndpointExporter serverEndpoint() {
            return new ServerEndpointExporter();
        }
    }
}
