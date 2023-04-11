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
    static final List<String> listLogs = new ArrayList<>();
    static final Path path = Paths.get(FilePath.FILE_PATH_LOG_STORAGE_FILE.getPath());

    public static List<String> readLogStorageFile() {

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                listLogs.add(currentLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listLogs;
    }

    public static void filterLogStorageFile() {

        List<String> listFilter = readLogStorageFile();
        listFilter.stream()
                .filter(it -> it.contains(" INFO org."))
                .forEach(System.out::println);
    }

    public static void filterHalfLogStorageFile() {
        List<String> listLogs = readLogStorageFile();
        List<String> listLogsHalf = listLogs.subList(listLogs.size() / 2, listLogs.size());
        long count = listLogsHalf.stream()
                .filter(it -> it.contains(" INFO org."))
                .count();
        System.out.println("In LogStorageFile is " + count + " records INFO");
    }

}
