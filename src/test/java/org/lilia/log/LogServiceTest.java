package org.lilia.log;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LogServiceTest {

    @Test
    void readLogStorageFile() {
        Set<String> expected = Set.of(
                "18-05-2023 22:30:06:482 ERROR org.lilia.ConsoleUtils invalid user input",
                "18-05-2023 22:30:06:486 WARNING org.lilia.ConsoleUtils unexpected input org.lilia.exception.InvalidUserInputException: expected String that matches pattern: [y|Y|n|N] , got: ooo",
                "18-05-2023 22:30:21:622 INFO org.lilia.view.HomeworkView selected to create homework"
        );
        try (BufferedReader reader = Files.newBufferedReader(Path.of(getClass().getClassLoader().getResource("checkFile.txt").getPath()))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                assertTrue(expected.contains(currentLine));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}