package org.lilia.log;

import org.lilia.serialization.FilePath;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogService {
    public static void readAndFilterLogStorageFile() {

        final List<String> list = new ArrayList<>();
        final Path path = Paths.get(FilePath.FILE_PATH_LOG_STORAGE_FILE.getPath());

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("INFO")) {
                    list.add("\n" + currentLine);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(list);
    }

}
