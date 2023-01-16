package org.lilia;

import org.lilia.repository.LectureRepository;
import org.lilia.repository.LectureRepositoryImpl;
import org.lilia.repository.TeacherRepository;
import org.lilia.repository.TeacherRepositoryImpl;
import org.lilia.service.CourseService;
import org.lilia.service.LectureService;
import org.lilia.service.MainService;
import org.lilia.service.PersonService;

import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        LectureRepository lectureRepository = new LectureRepositoryImpl();
        LectureService lectureService = new LectureService(lectureRepository);
        CourseService courseService = new CourseService();
        TeacherRepository teacherRepository = new TeacherRepositoryImpl();
        PersonService personService = new PersonService(teacherRepository);// todo name of variable
        MainService mainService = new MainService(courseService, lectureService, personService);

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
