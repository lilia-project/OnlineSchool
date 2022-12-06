package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.service.CourseService;
import org.lilia.service.LectureService;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to Online school!");
        System.out.println("continue working?\nY - Continue\nN - Exit");
        String labelCreate = SCANNER.nextLine();
        labelCreate = checkLabelCreate(labelCreate); //  check the validity labelCreate (only Y or N)

        if (labelCreate.equalsIgnoreCase("Y")) {

            CourseService courseService = new CourseService();
            LectureService lectureService = new LectureService();

            Course course1 = courseService.createCourse(1);

            int category = choiceCategory(); //choice of category
            outputSelectedCategory(category); //output of category

            System.out.println("_______________________");

            System.out.println("would you create a new lecture?\nY - Yes\nN - No\ninput Y or N");

            labelCreate = SCANNER.nextLine();
            labelCreate = checkLabelCreate(labelCreate); //  check the validity labelCreate (only Y or N)

            while (labelCreate.equalsIgnoreCase("Y")) {

                System.out.print("input name of lecture ");
                String nameLecture = SCANNER.nextLine();

                Lecture lecture = lectureService.createLecture(course1.id, nameLecture);
                System.out.println(lecture.toString());

                //check of limit 'lecture'
                if (Lecture.counter >= 8) {
                    System.out.println("limit has been reached");
                    break;
                } else {
                    System.out.println("would you create a new lecture?\nY - Yes\nN - No\ninput Y or N");
                    labelCreate = SCANNER.nextLine();
                    labelCreate = checkLabelCreate(labelCreate);
                }
            }
        } else {
            SCANNER.close();
        }
        System.out.println("Total created " + Lecture.counter + " lectures");
        SCANNER.close();
    }

    private static void outputSelectedCategory(int category) {
        //default -> System.out.println("No such category exists");
        switch (category) {
            case 1:
                System.out.println("you selected category 'Course'");
                break;
            case 2:
                System.out.println("you selected category 'Lecture'");
                break;
            case 3:
                System.out.println("you selected category 'Teacher'");
                break;
            case 4:
                System.out.println("you selected category 'Student'");
                break;
        }
    }

    private static int choiceCategory() {
        System.out.println("select a category:");
        System.out.println("Course - select 1");
        System.out.println("Lecture - select 2");
        System.out.println("Teacher - select 3");
        System.out.println("Student - select 4");
        System.out.print("enter number of category: ");

        int category = readInteger();
        return checkNumberOfCategory(category);
    }

    private static int checkNumberOfCategory(int category) {  //check the validity 'category'
        while (category < 1 || category > 4) {
            System.out.println("try agan (number must be from 1 to 4)");
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

    private static void feedNewLine() { //scanner processing for button enter
        SCANNER.nextLine();
    }
}
