package org.lilia;

import org.lilia.View.CourseView;
import org.lilia.View.HomeworkView;
import org.lilia.View.LectureView;
import org.lilia.View.PersonView;
import org.lilia.repository.*;
import org.lilia.service.CourseService;
import org.lilia.service.HomeworkService;
import org.lilia.service.LectureService;
import org.lilia.service.PersonService;

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
        LectureView lectureView = new LectureView();
        HomeworkView homeworkView = new HomeworkView(lectureService);
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
                    System.out.println("you selected category 'Course'\nchoice the action");
                    courseView.workWithCourse(courseService);
                }
                case 2 -> {
                    System.out.println("you selected category 'Lecture'\nchoice the action");
                    lectureView.workWithLectures(lectureService);
                }
                case 3 -> {
                    System.out.println("you selected category 'Teacher'\nchoice the action");
                    personView.workWithPerson(personService);
                }
                case 4 -> {
                    System.out.println("you selected category 'Student'\nchoice the action");
                    personView.workWithPerson(personService);
                }
                case 5 -> {
                    System.out.println("you selected category 'Homework'\nchoice the action");
                    homeworkView.workWithHomework(homeworkService);
                }
                case 6 -> System.out.print("Do you want finish or ");
                default -> System.out.println("Error - incompatible symbol");
            }
            System.out.println(question);
            userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
        }
        SCANNER.close();
    }
}
