package org.lilia.log;

public final class LoggerFactory {
    private static final LogStorage LOG_STORAGE = new LogStorage();

    private LoggerFactory() {
    }

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = new Logger(clazz.getName(), LOG_STORAGE);
        return logger;
    }
}
