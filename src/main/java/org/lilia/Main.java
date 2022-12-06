package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.models.Teacher;
import org.lilia.service.CourseService;
import org.lilia.service.LectureService;
import org.lilia.service.TeacherService;

import java.io.BufferedReader;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CourseService courseService = new CourseService();
        Course course1 = courseService.createCourse(1);

        LectureService lectureService = new LectureService();

        System.out.println("select a category:");
        System.out.println("Course - enter 1");
        System.out.println("Lecture - enter 2");
        System.out.println("Teacher - enter 3");
        System.out.println("Student - enter 4");
        System.out.print("enter number: ");

        int category = scanner.nextInt();

        switch (category) {
            case 1 -> System.out.println("you selected category Course");
            case 2 -> System.out.println("you selected category Lecture");
            case 3 -> System.out.println("you selected category Teacher");
            case 4 -> System.out.println("you selected category Student");
            default -> System.out.println("No such category exists");
        }
        System.out.println("_______________________");

        String labelCreate;
        System.out.println("would you create a new lecture?");
        do {
            System.out.println("input Y or N");
            labelCreate = scanner.next();
        } while (!labelCreate.equalsIgnoreCase("Y") && !labelCreate.equalsIgnoreCase("N"));

        while (labelCreate.equalsIgnoreCase("Y")) {

            System.out.print("input id of lecture ");
            int id = scanner.nextInt();

            System.out.print("input name of lecture ");
            scanner.nextLine();
            String nameLecture = scanner.nextLine();

            Lecture lecture = lectureService.createLecture(id, course1.id, nameLecture);
            System.out.println("you created new lecture:");
            System.out.println("id lecture = " + lecture.id);
            System.out.println("id course = " + course1.id);
            System.out.println("name of lecture - " + nameLecture);
            System.out.println("You created " + Lecture.counter + " lectures");
            System.out.println("------------------------");

            System.out.println("would you create a new lecture?");
            System.out.println("input Y or N");
            scanner.nextLine();
            labelCreate = scanner.nextLine();
        }
        scanner.close();
        System.out.println("Total created " + Lecture.counter + " lectures");
    }
}
