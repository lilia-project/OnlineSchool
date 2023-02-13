package org.lilia;

import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.repository.*;
import org.lilia.service.*;
import org.lilia.view.*;

import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchMaterialIdException {

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
        PersonView personView = new PersonView();

        System.out.println("\nWelcome to Online school!");
        String question = "continue working? Y - Continue N - Exit";
        System.out.println(question);
        String userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");

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
                    System.out.println("you selected category 'Student'\nchoice the action");
                    personView.workWithPerson(personService);
                }
                case 5 -> {
                    ConsoleUtils.print(Constants.ACTION);
                    homeworkView.workWithHomework(homeworkService);
                }
                case 6 -> {
                    ConsoleUtils.print(Constants.ACTION);
                    additionalMaterialView.workWithAdditionalMaterials(additionalMaterialService);
                }
                case 7 -> System.out.print("Do you want finish or ");
                default -> ConsoleUtils.print(Constants.ERROR + "incompatible symbol");
            }
            System.out.println(question);
            userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
        }
        SCANNER.close();
    }
}
