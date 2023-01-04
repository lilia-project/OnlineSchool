package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Person;
import org.lilia.repository.LectureRepository;
import org.lilia.repository.TeacherRepository;
import org.lilia.service.CourseService;
import org.lilia.service.LectureService;
import org.lilia.service.MainService;
import org.lilia.service.PersonService;

import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        LectureRepository lectureRepository = new LectureRepository();
        LectureService lectureService = new LectureService(lectureRepository);
        CourseService courseService = new CourseService();
        TeacherRepository teacherRepository = new TeacherRepository();
        PersonService personService = new PersonService(teacherRepository);
        MainService mainService = new MainService(courseService, lectureService, personService);

        System.out.println("input course's name\n");
        String courseName = mainService.readAndValidationInput("^[a-zA-Z]+\\d*");

        Course course = courseService.createCourse(courseName);

        System.out.println("input teacher's firstName\n");
        String firstName = mainService.readAndValidationInput("[a-zA-Z]+");
        System.out.println("input teacher's lastName\n");
        String lastName = mainService.readAndValidationInput("[a-zA-Z]+");
        System.out.println("input teacher's phone\n");
        String phone = mainService.readAndValidationInput("\\+\\d{3}");
        System.out.println("input teacher's email\n");
        String email = mainService.readAndValidationInput("\\w+@\\w+\\.\\w+");

        Person teacher = personService.createPerson(course.getId(), firstName, lastName, phone, email);

        mainService.autoCreateLectures(lectureService, course, teacher.getId());

        System.out.println("\nWelcome to Online school!");
        String question = "continue working? Y - Continue N - Exit";
        System.out.println(question);
        String userChoice = mainService.readAndValidationInput("[y|Y|n|N]");

        while (userChoice.equalsIgnoreCase("Y")) {

            int category = mainService.choiceCategory();// todo validation [1-5]

            switch (category) {
                case 1 -> {
                    System.out.println("you selected category 'Course'\nchoice the action");
                    mainService.workWithCourse(courseService, course);
                }
                case 2 -> {
                    System.out.println("you selected category 'Lecture'\nchoice the action");
                    mainService.workWithLectures(lectureService, course);
                }
                case 3 -> {
                    System.out.println("you selected category 'Teacher'\nchoice the action");
                    mainService.workWithPerson(personService, course);
                }
                case 4 -> {
                    System.out.println("you selected category 'Student'\nchoice the action");
                    mainService.workWithPerson(personService, course);
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
