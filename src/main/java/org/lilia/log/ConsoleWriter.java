package org.lilia.log;

public class ConsoleWriter implements LogWriter {
    @Override
    public void write(Log log) {
        if (log.getLogLevel().equals(LogLevel.ERROR.getField())) {
            System.err.println(log);
        } else {
            System.out.println(log);
        }
    }
}
