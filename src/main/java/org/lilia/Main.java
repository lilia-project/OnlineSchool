package org.lilia;

import org.lilia.models.Course;
import org.lilia.repository.LectureRepository;
import org.lilia.service.CourseService;
import org.lilia.service.LectureService;
import org.lilia.service.MainService;

import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        LectureRepository lectureRepository = new LectureRepository();
        LectureService lectureService = new LectureService(lectureRepository);
        CourseService courseService = new CourseService();
        MainService mainService = new MainService(courseService,lectureService);

        Course course = courseService.createCourse();

        mainService.autoCreate(lectureService, course);

        System.out.println("Welcome to Online school!");
        String question = "continue working? Y - Continue N - Exit";
        String checkContinueWorking = mainService.getCheckContinueWorking(question);

        while (checkContinueWorking.equalsIgnoreCase("Y")) {

            int category = mainService.choiceCategory();
            System.out.println("_______________________");

            switch (category) {
                case 1:
                    System.out.println("you selected category 'Course'");
                    break;
                case 2:
                    System.out.println("you selected category 'Lecture'\nchoice the action");
                    mainService.workWithLectures(lectureService, course);
                    break;
                case 3:
                    System.out.println("you selected category 'Teacher'");
                    break;
                case 4:
                    System.out.println("you selected category 'Student'");
                    break;
                case 5:
                    System.out.print("Do you want finish or ");
                    break;
                default:

                   // System.out.println("Error - incompatible symbol");
                    break;
            }
            checkContinueWorking = mainService.getCheckContinueWorking(question);
        }
        SCANNER.close();
    }


}
