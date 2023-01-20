package org.lilia;

import org.lilia.repository.*;
import org.lilia.service.*;

import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        LectureRepository lectureRepository = new LectureRepositoryImpl();
        HomeworkRepository homeworkRepository = new HomeworkRepositoryImpl();
        HomeworkService homeworkService = new HomeworkService(homeworkRepository);
        LectureService lectureService = new LectureService(lectureRepository, homeworkService);
        CourseService courseService = new CourseService();
        TeacherRepository teacherRepository = new TeacherRepositoryImpl();
        PersonService personService = new PersonService(teacherRepository);
        MainService mainService = new MainService(courseService, lectureService, personService, homeworkService);

        System.out.println("\nWelcome to Online school!");
        String question = "continue working? Y - Continue N - Exit";
        System.out.println(question);
        String userChoice = mainService.readAndValidationInput("[y|Y|n|N]");

        while (userChoice.equalsIgnoreCase("Y")) {

            int category = mainService.choiceCategory();

            switch (category) {
                case 1 -> {
                    System.out.println("you selected category 'Course'\nchoice the action");
                    mainService.workWithCourse(courseService);
                }
                case 2 -> {
                    System.out.println("you selected category 'Lecture'\nchoice the action");
                    mainService.workWithLectures(lectureService);
                }
                case 3 -> {
                    System.out.println("you selected category 'Teacher'\nchoice the action");
                    mainService.workWithPerson(personService);
                }
                case 4 -> {
                    System.out.println("you selected category 'Student'\nchoice the action");
                    mainService.workWithPerson(personService);
                }
                case 5 -> System.out.print("Do you want finish or ");
                default -> System.out.println("Error - incompatible symbol");
            }
            System.out.println(question);
            userChoice = mainService.readAndValidationInput("[y|Y|n|N]");
        }
        SCANNER.close();
    }
}
