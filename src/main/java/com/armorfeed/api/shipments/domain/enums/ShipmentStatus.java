package com.armorfeed.api.shipments.domain.enums;

public enum ShipmentStatus {
    CREATED("CREATED"),
    IN_PROGRESS("IN_PROGRESS"),
    PAUSED("PAUSED"),
    DELETED("DELETED"),
    ABORTED("ABORTED"),
    FINISHED("FINISHED");

    private String status;
    
    private ShipmentStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
}
