package com.github.hrozhek.noizalyzerserver.repo;

import com.github.hrozhek.noizalyzerserver.config.DirectoryConfig;
import com.github.hrozhek.noizalyzerserver.exception.BaseException;
import com.github.hrozhek.noizalyzerserver.model.FileData;
import com.github.hrozhek.noizalyzerserver.util.ByteBufferUtil;
import com.github.hrozhek.noizalyzerserver.util.InstantConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemFileRepoImpl implements FileRepo {

    private final DirectoryConfig config;

    @Autowired
    public FileSystemFileRepoImpl(DirectoryConfig config) {
        this.config = config;
    }

    @Override
    public void save(ByteBuffer buffer, FileData data) {
        Path file = createFile(data);
        try {
            FileCopyUtils.copy(ByteBufferUtil.toArray(buffer), file.toFile());
        } catch (IOException e) {
            throw new BaseException();//todo
        }
    }

    private Path createFile(FileData data) {
        try {
            Path dir = Paths.get(config.getAppRootPath().toString(), data.getControllerId());
            if (!Files.isDirectory(dir)) {
                dir = Files.createDirectories(dir);
            }
            String fileName = String.format("%s_sensorId_%d_%s", config.getPrefix(), data.getSensorId(),
                    InstantConverter.format(data.getCreationTime()));
            Path file = dir.resolve(fileName);
            if (Files.isRegularFile(file)) {
                throw new BaseException();//todo
            }
            return Files.createFile(file);
        } catch (IOException e) {
            throw new BaseException();//todo
        }
    }

    @Override
    public void delete() {
        //todo
    }
}
