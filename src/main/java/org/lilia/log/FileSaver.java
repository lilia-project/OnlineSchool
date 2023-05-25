package org.lilia.log;

import org.lilia.serialization.FilePath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileSaver implements LogWriter {

    @Override
    public void write(Log log) {

        String stringLog = log.toString();
        try {
            final Path path = Path.of(FilePath.FILE_PATH_LOG_STORAGE_FILE.getPath());
            if (!Files.exists(path)) {
                throw new IllegalStateException();
            }
            Files.write(path, System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
            Files.write(path, stringLog.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
