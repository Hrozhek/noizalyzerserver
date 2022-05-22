package com.github.hrozhek.noizalyzerserver.processor;

import com.github.hrozhek.noizalyzerserver.repo.ControllerRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.*;

public class KeepAliveProcessorImpl implements KeepAliveProcessor {

    private final ControllerRepo controllerRepo;
//    private final ConcurrentMap<> todo - update

    @Autowired
    public KeepAliveProcessorImpl(ControllerRepo controllerRepo) {
        this.controllerRepo = controllerRepo;
        initExecutorService();
    }

    private void initExecutorService() {
        ThreadFactory namedDaemonFactory = runnable -> {
            Thread thread = new Thread(runnable, "KeepAliveProcessorThread");
            thread.setDaemon(true);
            return thread;
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4, namedDaemonFactory);//todo
        executor.schedule(this::checkAllControllers, 1, TimeUnit.MINUTES);//todo from config
    }

    @Override
    public void ping() {//todo endpoint method

    }

    private void checkAllControllers() {
        Collection<UUID> activeControllers = controllerRepo.getAllIds();
        //todo addAll and then subtract
    }
}
