package org.lilia;

import org.lilia.constant.Constants;
import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.log.ConfigurationReader;
import org.lilia.log.ConfigurationWatcher;
import org.lilia.log.LogService;
import org.lilia.log.LoggerFactory;
import org.lilia.network.SelectorClient;
import org.lilia.network.SelectorServer;
import org.lilia.repository.CourseRepository;
import org.lilia.repository.DataBaseInitializer;
import org.lilia.service.*;
import org.lilia.util.ConsoleUtils;
import org.lilia.view.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchMaterialIdException, InterruptedException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        HomeworkService homeworkService = context.getBean("homeworkService", HomeworkService.class);
        AdditionalMaterialService additionalMaterialService = context.getBean("additionalMaterialService", AdditionalMaterialService.class);
        LectureService lectureService = context.getBean("lectureService", LectureService.class);
        CourseService courseService = context.getBean("courseService", CourseService.class);
        PersonService personService = context.getBean("personService", PersonService.class);
        LectureView lectureView = context.getBean("lectureView", LectureView.class);
        HomeworkView homeworkView = context.getBean("homeworkView", HomeworkView.class);
        AdditionalMaterialView additionalMaterialView = context.getBean("additionalMaterialView", AdditionalMaterialView.class);
        CourseView courseView = context.getBean("courseView", CourseView.class);
        PersonView personView = context.getBean("personView", PersonView.class);
        ControlWorkService controlWorkService = context.getBean("controlWorkService", ControlWorkService.class);
        ConfigurationReader configurationReader = context.getBean("configurationReader", ConfigurationReader.class);

        ConfigurationWatcher configurationWatcher = new ConfigurationWatcher(LoggerFactory.CONSOLE_WRITER, configurationReader);

        ConsoleUtils.print("\nWelcome to Online school!");

        CourseRepository courseRepository = context.getBean("courseRepository", CourseRepository.class);
        courseRepository.getAllCourses();
        DataBaseInitializer dataBaseInitializer = context.getBean(DataBaseInitializer.class);

        dataBaseInitializer.fillTables();

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
                case 9 -> {
                    LogService.filterLogStorageFile();
                    LogService.filterHalfLogStorageFile();
                }
                case 0 -> ConsoleUtils.print("Do you want finish or ");
                default -> ConsoleUtils.print(Constants.ERROR + "incompatible symbol");
            }
            ConsoleUtils.print(Constants.CONTINUE);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
        SCANNER.close();
        context.close();

    }

    private static void startServer() {
        Thread serverThread = new Thread(() -> {
            try {
                new SelectorServer().start();
                ConsoleUtils.print("Server started");
            } catch (IOException e) {
                ConsoleUtils.print("Server is not able to start, details: " + e.getMessage());
            }
        });
        serverThread.start();
    }

    private static void startClient() throws InterruptedException {
        Thread clientThread = new Thread(() -> {
            try {
                new SelectorClient().start();
                ConsoleUtils.print("Client started");
            } catch (IOException e) {
                ConsoleUtils.print("Client is not able to start, details: " + e.getMessage());
            }
        });
        clientThread.start();
        clientThread.join(10000);
        clientThread.interrupt();
    }
}
