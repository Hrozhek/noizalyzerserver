package com.github.hrozhek.noizalyzerserver.config;

import java.nio.file.Path;
import java.util.Objects;

public class DirectoryConfig {

    private final Path appRootPath;
    private final String prefix;

    public DirectoryConfig(Path appRootPath, String prefix) {
        checkPath(appRootPath);
        this.appRootPath = appRootPath;
        this.prefix = prefix;
    }

    private void checkPath(Path path) {
        //todo mock
    }

    public Path getAppRootPath() {
        return appRootPath;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return "DirectoryConfig{" +
                "appRootPath=" + appRootPath +
                " prefix=" + prefix +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryConfig that = (DirectoryConfig) o;
        return Objects.equals(appRootPath, that.appRootPath) && Objects.equals(prefix, that.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appRootPath, prefix);
    }
}
