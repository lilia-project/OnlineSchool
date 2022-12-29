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

        Course course = courseService.createCourse("firstCourse");

        Person teacher = personService.createPerson(course.getId(), null);
        System.out.println("\n from main\n idCourse = " + course.getId() + "\n personId = " + teacher.getId() +
                "\n nameTeacher is null");

        mainService.autoCreateLectures(lectureService, course, teacher.getId());

        System.out.println("\nWelcome to Online school!");
        String question = "continue working? Y - Continue N - Exit";
        String checkContinueWorking = mainService.getCheckContinueWorking(question);

        while (checkContinueWorking.equalsIgnoreCase("Y")) {

            int category = mainService.choiceCategory();
            System.out.println("_______________________");

            switch (category) {
                case 1 -> System.out.println("you selected category 'Course'");
                case 2 -> {
                    System.out.println("you selected category 'Lecture'\nchoice the action");
                    mainService.workWithLectures(lectureService, course);
                }
                case 3 -> System.out.println("you selected category 'Teacher'");
                case 4 -> System.out.println("you selected category 'Student'");
                case 5 -> System.out.print("Do you want finish or ");
                default -> System.out.println("Error - incompatible symbol");
            }
            checkContinueWorking = mainService.getCheckContinueWorking(question);
        }
        SCANNER.close();
    }


}
