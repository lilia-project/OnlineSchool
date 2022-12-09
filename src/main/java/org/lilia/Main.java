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

        MainService mainService = new MainService();
        CourseService courseService = new CourseService();
        LectureRepository lectureRepository = new LectureRepository();
        LectureService lectureService = new LectureService(lectureRepository);

        Course course = courseService.createCourse();

        System.out.println("Welcome to Online school!");
        String question = "continue working? Y - Continue N - Exit";
        String labelContinueWorking = mainService.getLabelContinueWorking(question);

        while (labelContinueWorking.equalsIgnoreCase("Y")) {

            int category = mainService.choiceCategory();
            System.out.println("_______________________");

            switch (category) {
                case 1 -> System.out.println("you selected category 'Course'");
                case 2 -> {
                    System.out.println("you selected category 'Lecture'");
                    mainService.workingWithLectures(lectureService, course, lectureRepository);
                }
                case 3 -> System.out.println("you selected category 'Teacher'");
                case 4 -> System.out.println("you selected category 'Student'");
                case 5 -> System.out.print("Do you want finish or ");
            }
            labelContinueWorking = mainService.getLabelContinueWorking(question);
        }
        SCANNER.close();
    }
}
