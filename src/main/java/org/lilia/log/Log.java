package org.lilia.log;

import java.time.LocalDateTime;

public class Log {
    private final String name;

    private final String logLevel;

    private final String message;
    private final LocalDateTime localDate;
    private String stackTrace;

    protected Log(String name, String logLevel, String message, String stackTrace) {
        this.name = name;
        this.logLevel = logLevel;
        this.message = message;
        this.localDate = LocalDateTime.now();
        this.stackTrace = stackTrace;
    }

    protected Log(String name, String logLevel, String message) {
        this.name = name;
        this.logLevel = logLevel;
        this.message = message;
        this.localDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        String log = localDate +
                " " + logLevel +
                " " + name +
                " " + message;
        if (stackTrace != null) {
            return log + " " + stackTrace;
        }
        return log;
    }

    public String getLogLevel() {
        return logLevel;
    }
}

