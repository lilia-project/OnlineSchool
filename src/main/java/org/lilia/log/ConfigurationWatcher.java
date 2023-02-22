package org.lilia.log;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;

public class ConfigurationWatcher extends Thread {
    private final ConsoleWriter consoleWriter;

    public ConfigurationWatcher(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
    }

    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
