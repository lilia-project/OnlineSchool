package org.lilia.service;

import org.lilia.models.Course;
import org.lilia.models.Lecture;

import java.util.Arrays;

import static org.lilia.Main.SCANNER;

public class MainService {

    private static String checkContinueWorking;
    private static int checkIntNumber;

    private final CourseService courseService;
    private final LectureService lectureService;
    private final PersonService personService;


    public MainService(CourseService courseService, LectureService lectureService, PersonService personService) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.personService = personService;
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
        System.out.println("2 - open/edit");
        System.out.println("3 - output all");
        System.out.println("4 - delete");
        System.out.println("5 - exit");

        checkIntNumber = readInteger();

        return getCheckIntNumber();
    }

    public void workWithLectures(LectureService lectureService, Course course) {

        switch (choiceAction()) {
            case 1:
                String question = "would you create a new lecture? Y - Yes N - No";
                while (checkContinueWorking.equalsIgnoreCase("Y")) {

                    System.out.println("input teacherId ");
                    //System.out.println("available teacherId");

                    System.out.println("available teacherId " + Arrays.toString(personService.getAllTeacherIds()));

                    int teacherId = readInteger();

                    System.out.print("input name of lecture ");
                    String lectureName = SCANNER.nextLine();
                    lectureService.createLecture(course.getId(), lectureName, teacherId, "descriptionLecture");

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
                System.out.println("open lecture number");
                int lectureId = readInteger();

                lectureService.getById(lectureId);
                break;
            case 3:
                System.out.println("the list of lectures");
                lectureService.out();
                break;
            case 4:
                System.out.println("input lecture number");
                lectureId = readInteger();
                lectureService.deleteById(lectureId);
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

    public void autoCreateLectures(LectureService lectureService, Course course, int personId) {
        String[] autoName = {"firstLecture", "secondLecture", "thirdLecture"};
        for (int i = 0; i < 3; i++) {
            lectureService.createLecture(course.getId(), autoName[i], personId, "descriptionLecture");
        }
    }
}

