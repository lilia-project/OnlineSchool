package org.lilia;

import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.log.ConfigurationReader;
import org.lilia.log.ConfigurationWatcher;
import org.lilia.log.LogService;
import org.lilia.log.LoggerFactory;
import org.lilia.network.SelectorClient;
import org.lilia.network.SelectorServer;
import org.lilia.repository.*;
import org.lilia.service.*;
import org.lilia.view.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchMaterialIdException, InterruptedException {

        LectureRepository lectureRepository = new LectureRepository();
        HomeworkRepository homeworkRepository = new HomeworkRepository();
        HomeworkService homeworkService = new HomeworkService(homeworkRepository);
        AdditionalMaterialRepository additionalMaterialRepository = new AdditionalMaterialRepository();
        AdditionalMaterialService additionalMaterialService = new AdditionalMaterialService(additionalMaterialRepository);
        LectureService lectureService = new LectureService(lectureRepository, homeworkService);
        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository, lectureService);
        PersonRepository personRepository = new PersonRepository();
        PersonService personService = new PersonService(personRepository);
        LectureView lectureView = new LectureView();
        HomeworkView homeworkView = new HomeworkView(lectureService);
        AdditionalMaterialView additionalMaterialView = new AdditionalMaterialView(lectureService);
        CourseView courseView = new CourseView();
        PersonView personView = new PersonView(courseService);
        ControlWorkService controlWorkService = new ControlWorkService();
        ConfigurationReader configurationReader = new ConfigurationReader();

        ConfigurationWatcher configurationWatcher = new ConfigurationWatcher(LoggerFactory.CONSOLE_WRITER, configurationReader);

        System.out.println("\nWelcome to Online school!");
        configurationWatcher.setDaemon(true);
        configurationWatcher.start();

        ConsoleUtils.print(Constants.CONTINUE);
        String userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

        while (userChoice.equalsIgnoreCase("Y")) {

            int category = ConsoleUtils.choiceCategory();

            switch (category) {
                case 1 -> {
                    ConsoleUtils.print(Constants.ACTION);
                    courseView.workWithCourse(courseService);
                }
                case 2 -> {
                    ConsoleUtils.print(Constants.ACTION);
                    lectureView.workWithLectures(lectureService);
                }
                case 3 -> {
                    ConsoleUtils.print(Constants.ACTION);
                    personView.workWithPerson(personService);
                }
                case 4 -> {
                    ConsoleUtils.print(Constants.ACTION);
                    homeworkView.workWithHomework(homeworkService);
                }
                case 5 -> {
                    ConsoleUtils.print(Constants.ACTION);
                    additionalMaterialView.workWithAdditionalMaterials(additionalMaterialService);
                }
                case 6 -> controlWorkService.startControlWork();
                case 7 -> startServer();
                case 8 -> startClient();
                case 0 -> LogService.readAndFilterLogStorageFile();
                case 9 -> ConsoleUtils.print("Do you want finish or ");
                default -> ConsoleUtils.print(Constants.ERROR + "incompatible symbol");
            }
            ConsoleUtils.print(Constants.CONTINUE);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
        SCANNER.close();
    }

    private static void startServer() {
        Thread serverThread = new Thread(() -> {
            try {
                new SelectorServer().start();
                System.out.println("Server started");
            } catch (IOException e) {
                System.out.println("Server is not able to start, details: " + e.getMessage());
            }
        });
        serverThread.start();
    }

    private static void startClient() throws InterruptedException {
        Thread clientThread = new Thread(() -> {
            try {
                new SelectorClient().start();
                System.out.println("Client started");
            } catch (IOException e) {
                System.out.println("Client is not able to start, details: " + e.getMessage());
            }
        });
        clientThread.start();
        clientThread.join(10000);
        clientThread.interrupt();
    }
}
