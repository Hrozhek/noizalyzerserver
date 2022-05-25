package com.github.hrozhek.noizalyzerserver.repo;

import com.github.hrozhek.noizalyzerserver.model.ControllerModel;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryControllerRepoImpl <M extends ControllerModel> implements ControllerRepo<M> {

    private final ConcurrentMap<UUID, M> models = new ConcurrentHashMap<>();

    //todo logging annotations
    public UUID add(M model) {
        UUID id = generateId(model);
        models.put(id, model);
        return id;
    }

    //todo answer or what
    @Override
    public void remove(UUID id) {
        models.remove(id);
    }

    @Override
    public M get(UUID id) {
        return models.get(id);
    }

    @Override
    public Collection<UUID> getAllIds() {
        return new HashSet<>(models.keySet());
    }

    private UUID generateId(ControllerModel model) {
        return UUID.randomUUID();
    }
}
