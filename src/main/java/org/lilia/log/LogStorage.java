package org.lilia.log;

import java.util.ArrayList;
import java.util.List;

public class LogStorage {

    private final List<Log> logStorage = new ArrayList<>();
    private final List<LogWriter> logWriters = List.of(new FileSaver(), new ConsoleWriter());

    public void save(Log log) {
        logStorage.add(log);
        for (LogWriter logWriter : logWriters) {
            logWriter.write(log);
        }
    }
}
