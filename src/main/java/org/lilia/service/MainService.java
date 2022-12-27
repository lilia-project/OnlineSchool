package org.lilia.service;

import org.lilia.models.Course;
import org.lilia.models.Lecture;

import static org.lilia.Main.SCANNER;

public class MainService {

    private static String checkContinueWorking;
    private static int checkIntNumber;

    private final CourseService courseService;
    private final LectureService lectureService;

    public MainService(CourseService courseService, LectureService lectureService) {
        this.courseService = courseService;
        this.lectureService = lectureService;
    }

    private static int readInteger() { //scanner processing for integer
        int id = SCANNER.nextInt();
        feedNewLine();
        return id;
    }

    private static void feedNewLine() { //scanner processing for button enter
        SCANNER.nextLine();
    }

    public String getCheckContinueWorking(String question) {
        System.out.println(question);
        checkContinueWorking = SCANNER.nextLine();
        while (!checkContinueWorking.equalsIgnoreCase("Y") && !checkContinueWorking.equalsIgnoreCase("N")) {
            System.out.println("input Y or N");
            checkContinueWorking = SCANNER.nextLine();
        }
        return checkContinueWorking;
    }

    public int choiceCategory() {
        System.out.println("select a category:");
        System.out.println("Course - select 1");
        System.out.println("Lecture - select 2");
        System.out.println("Teacher - select 3");
        System.out.println("Student - select 4");
        System.out.println("Exit - select 5");

        checkIntNumber = readInteger();

        return getCheckIntNumber();
    }

    private int getCheckIntNumber() {
        while (checkIntNumber < 1 || checkIntNumber > 5) {
            System.out.println("try agan (number must be from 1 to 5)");
            checkIntNumber = readInteger();
        }
        return checkIntNumber;
    }

    public int choiceAction() {
        System.out.println("1 - create new");
        System.out.println("2 - edit");
        System.out.println("3 - delete");
        System.out.println("4 - output all");
        System.out.println("5 - exit");

        checkIntNumber = readInteger();

        return getCheckIntNumber();
    }

    public void workWithLectures(LectureService lectureService, Course course) {

        switch (choiceAction()) {
            case 1:
                String question = "would you create a new lecture? Y - Yes N - No";
                while (checkContinueWorking.equalsIgnoreCase("Y")) {
                    System.out.print("input name of lecture ");
                    String nameLecture = SCANNER.nextLine();
                    lectureService.createLecture(course.id, nameLecture);

                    if (Lecture.counter >= 8) {
                        System.out.println("limit has been reached");
                        break;
                    } else {
                        checkContinueWorking = getCheckContinueWorking(question);
                    }
                }
                System.out.println("Total created " + Lecture.counter + " lectures");
                break;
            case 2:
                System.out.println("edit");
                break;
            case 3:
                System.out.println("delete");
                break;
            case 4:
                System.out.println("number of lectures");
                lectureService.out();
                break;
            case 5:
                System.out.println("exit");
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    public void autoCreateLectures(LectureService lectureService, Course course) {
        for (int i = 0; i < 3; i++) {
            lectureService.createLecture(course.id);
        }
    }
}

