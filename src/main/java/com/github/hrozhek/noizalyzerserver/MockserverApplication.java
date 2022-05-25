package com.github.hrozhek.noizalyzerserver;

import com.github.hrozhek.noizalyzerserver.config.DirectoryConfig;
import com.github.hrozhek.noizalyzerserver.context.ApplicationContext;
import com.github.hrozhek.noizalyzerserver.repo.ControllerRepo;
import com.github.hrozhek.noizalyzerserver.repo.FileRepo;
import com.github.hrozhek.noizalyzerserver.repo.FileSystemFileRepoImpl;
import com.github.hrozhek.noizalyzerserver.repo.InMemoryControllerRepoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;
import java.nio.file.Paths;
@SpringBootApplication
public class MockserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockserverApplication.class, args);
    }

    @Bean
    public ApplicationContext getApplicationContext() {
        return new ApplicationContext();
    }

    @Bean
    public FileRepo getFileRepo() {
        try {
            Path root = Paths.get("C:\\Projects\\noize");
            return new FileSystemFileRepoImpl(new DirectoryConfig(root, "record"));
        } catch (Exception e) {
            throw new RuntimeException(e);//todo
        }
    }

    @Bean
    public ControllerRepo getControllerRepo() {
        return new InMemoryControllerRepoImpl();
    }
}
