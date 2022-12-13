package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.service.CourseService;
import org.lilia.service.LectureService;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        CourseService courseService = new CourseService();
        LectureService lectureService = new LectureService();

        Course course1 = courseService.createCourse(1);

        System.out.println("Welcome to Online school!");
        String question = "continue working?\nY - Continue\nN - Exit";
        String labelContinueWorking = getLabelContinueWorking(question);

        while (labelContinueWorking.equalsIgnoreCase("Y")) {

            int category = choiceCategory();
            System.out.println("_______________________");

            switch (category) {
                case 1 -> System.out.println("you selected category 'Course'");
                case 2 -> {
                    System.out.println("you selected category 'Lecture'");
                    workingWithLecture(lectureService, course1);
                }
                case 3 -> System.out.println("you selected category 'Teacher'");
                case 4 -> System.out.println("you selected category 'Student'");
            }
            question = "continue working?\nY - Continue\nN - Exit";
            labelContinueWorking = getLabelContinueWorking(question);
        }
        SCANNER.close();
    }

    private static String getLabelContinueWorking(String question) {
        System.out.println(question);
        String labelContinueWorking = SCANNER.nextLine();
        while (!labelContinueWorking.equalsIgnoreCase("Y") && !labelContinueWorking.equalsIgnoreCase("N")) {
            System.out.println("input Y or N");
            labelContinueWorking = SCANNER.nextLine();
        }
        return labelContinueWorking;
    }

    private static void workingWithLecture(LectureService lectureService, Course course1) {
        String question = "would you create a new lecture?\nY - Yes\nN - No\ninput Y or N";
        String labelContinueWorking = getLabelContinueWorking(question);

        while (labelContinueWorking.equalsIgnoreCase("Y")) {

            System.out.print("input name of lecture ");
            String nameLecture = SCANNER.nextLine();

            Lecture lecture = lectureService.createLecture(course1.id, nameLecture);

            System.out.println(lecture.toString());

            if (Lecture.counter >= 8) {
                System.out.println("limit has been reached");
                break;
            } else {
                labelContinueWorking = getLabelContinueWorking(question);
            }
        }
        System.out.println("Total created " + Lecture.counter + " lectures");
    }

    private static int choiceCategory() {
        System.out.println("select a category:");
        System.out.println("Course - select 1");
        System.out.println("Lecture - select 2");
        System.out.println("Teacher - select 3");
        System.out.println("Student - select 4");
        System.out.print("enter number of category: ");

        int category = readInteger();

        while (category < 1 || category > 4) {
            System.out.println("try agan (number must be from 1 to 4)");
            category = readInteger();
        }
        return category;
    }

    private static int readInteger() { //scanner processing for integer
        int id = SCANNER.nextInt();
        feedNewLine();
        return id;
    }

    private static void feedNewLine() { //scanner processing for button enter
        SCANNER.nextLine();
    }
}
