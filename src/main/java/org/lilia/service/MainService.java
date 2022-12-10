package org.lilia.service;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.repository.LectureRepository;

import static org.lilia.Main.SCANNER;

public class MainService {
    String labelContinueWorking;

    private static int readInteger() { //scanner processing for integer
        int id = SCANNER.nextInt();
        feedNewLine();
        return id;
    }

    private static void feedNewLine() { //scanner processing for button enter
        SCANNER.nextLine();
    }

    public String getLabelContinueWorking(String question) {
        System.out.println(question);
        labelContinueWorking = SCANNER.nextLine();
        while (!labelContinueWorking.equalsIgnoreCase("Y") && !labelContinueWorking.equalsIgnoreCase("N")) {
            System.out.println("input Y or N");
            labelContinueWorking = SCANNER.nextLine();
        }
        return labelContinueWorking;
    }

    public int choiceCategory() {
        System.out.println("select a category:");
        System.out.println("Course - select 1");
        System.out.println("Lecture - select 2");
        System.out.println("Teacher - select 3");
        System.out.println("Student - select 4");
        System.out.println("Exit - select 5");

        int category = readInteger();

        while (category < 1 || category > 5) {
            System.out.println("try agan (number must be from 1 to 5)");
            category = readInteger();
        }
        return category;
    }

    public void workWithLectures(LectureService lectureService, Course course, LectureRepository lectureRepository) {
        String question = "would you create a new lecture? Y - Yes N - No";
        labelContinueWorking = getLabelContinueWorking(question);

        while (labelContinueWorking.equalsIgnoreCase("Y")) {

            System.out.print("input name of lecture ");
            String nameLecture = SCANNER.nextLine();
            lectureService.createLecture(course.id, nameLecture);

            if (Lecture.counter >= 8) {
                System.out.println("limit has been reached");
                break;
            } else {
                labelContinueWorking = getLabelContinueWorking(question);
            }
        }
        System.out.println("Total created " + Lecture.counter + " lectures");
    }

    public void autoCreate(LectureService lectureService, Course course) {
        int autoCreateLectures = 0;
        while (autoCreateLectures < 3) {
            lectureService.createLecture(course.id);
            autoCreateLectures++;
        }
    }
}
