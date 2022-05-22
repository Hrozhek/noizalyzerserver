package com.github.hrozhek.noizalyzerserver.model;

import java.time.Instant;
import java.util.Objects;

public class FileData {

    private final String controllerId;
    private final int sensorId;
    private final Instant creationTime;

    public FileData(String controllerId, int sensorId) {
        creationTime = Instant.now();
        this.controllerId = controllerId;
        this.sensorId = sensorId;
    }

    public String getControllerId() {
        return controllerId;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public int getSensorId() {
        return sensorId;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "controllerId='" + controllerId + '\'' +
                ", sensorId='" + sensorId + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileData fileData = (FileData) o;
        return sensorId == fileData.sensorId && Objects.equals(controllerId, fileData.controllerId) && Objects.equals(creationTime, fileData.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(controllerId, sensorId, creationTime);
    }
}
