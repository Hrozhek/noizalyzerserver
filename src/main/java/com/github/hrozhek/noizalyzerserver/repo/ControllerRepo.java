package com.github.hrozhek.noizalyzerserver.repo;

import com.github.hrozhek.noizalyzerserver.model.ControllerModel;

import java.util.Collection;
import java.util.UUID;

public interface ControllerRepo<M extends ControllerModel> {

    //todo return response
    UUID add(M model);//todo callback?

    void remove(UUID id);

    M get(UUID id);

    Collection<UUID> getAllIds();
}
