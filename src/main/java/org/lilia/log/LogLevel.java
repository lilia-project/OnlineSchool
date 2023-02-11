package org.lilia.log;

public enum LogLevel {
    ERROR("ERROR"),
    WARNING("WARNING"),
    INFO("INFO"),
    DEBUG("DEBUG");

    private final String field;

    LogLevel(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
