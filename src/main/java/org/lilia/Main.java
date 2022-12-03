package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.service.CourseService;
import org.lilia.service.LectureService;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("welcome to Online school!");
        System.out.println("continue working?\nY - Continue\nN - Exit");
        String labelCreate = SCANNER.nextLine();
        labelCreate = checkLabelCreate(labelCreate);
        if (labelCreate.equalsIgnoreCase("Y")) {

            CourseService courseService = new CourseService();
            Course course1 = courseService.createCourse(1);

            LectureService lectureService = new LectureService();

            int category = choiceCategory();
            System.out.println(category);

            switch (category) {
                case 1 -> System.out.println("you selected category 'Course'");
                case 2 -> System.out.println("you selected category 'Lecture'");
                case 3 -> System.out.println("you selected category 'Teacher'");
                case 4 -> System.out.println("you selected category 'Student'");
                //default -> System.out.println("No such category exists");
            }
            System.out.println("_______________________");

            System.out.println("would you create a new lecture?\nY - Yes\nN - No\ninput Y or N");

            labelCreate = SCANNER.nextLine();
            labelCreate = checkLabelCreate(labelCreate);

            while (labelCreate.equalsIgnoreCase("Y")) {

                System.out.print("input id of lecture ");
                int id = readInteger();

                System.out.print("input name of lecture ");
                String nameLecture = SCANNER.nextLine();

                Lecture lecture = lectureService.createLecture(id, course1.id, nameLecture);
                System.out.println("you created new lecture:");
                System.out.println("id lecture = " + lecture.id);
                System.out.println("id course = " + course1.id);
                System.out.println("name of lecture - " + nameLecture);
                System.out.println("You created " + Lecture.counter + " lectures");
                System.out.println("------------------------");

                System.out.println("would you create a new lecture?\nY - Yes\nN - No\ninput Y or N");

                labelCreate = SCANNER.nextLine();
                labelCreate = checkLabelCreate(labelCreate);
            }

            System.out.println("Total created " + Lecture.counter + " lectures");
        } else {
            SCANNER.close();
        }
    }

    private static int choiceCategory() {
        System.out.println("select a category:");
        System.out.println("Course - select 1");
        System.out.println("Lecture - select 2");
        System.out.println("Teacher - select 3");
        System.out.println("Student - select 4");
        System.out.print("enter number: ");

        int category = readInteger();
        category = checkNumberOfCategory(category);
        return category;
    }

    private static int checkNumberOfCategory(int category) {
        while (category < 1 || category > 4) {
            System.out.println("number is invalid\ntry agan");
            category = readInteger();
        }
        return category;
    }

    private static String checkLabelCreate(String labelCreate) {
        while (!labelCreate.equalsIgnoreCase("Y") && !labelCreate.equalsIgnoreCase("N")) {
            System.out.println("input Y or N");
            labelCreate = SCANNER.nextLine();
        }
        return labelCreate;
    }

    private static int readInteger() { //scanner processing for integer
        int id = SCANNER.nextInt();
        feedNewLine();
        return id;
    }

    private static void feedNewLine() { //scanner processing for enter
        SCANNER.nextLine();
    }
}
