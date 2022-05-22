package com.github.hrozhek.noizalyzerserver.repo;

import com.github.hrozhek.noizalyzerserver.model.FileData;

import java.nio.ByteBuffer;

public interface FileRepo {

    void save(ByteBuffer buffer, FileData fileData);

    void delete();

    //todo metadata getAll()
}
