package org.lilia.service;

import org.lilia.models.Course;
import org.lilia.models.Lecture;

import java.util.Arrays;

import static org.lilia.Main.SCANNER;

public class MainService {

    private static int checkIntNumber;

    private final CourseService courseService;
    private final LectureService lectureService;
    private final PersonService personService;

    public MainService(CourseService courseService, LectureService lectureService, PersonService personService) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.personService = personService;
    }

    public static int readInteger() { //scanner processing for integer
        int id = SCANNER.nextInt();
        feedNewLine();
        return id;
    }

    private static void feedNewLine() { //scanner processing for button enter
        SCANNER.nextLine();
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

    public void workWithCourse(CourseService courseService, Course course) {
    }

    public void workWithLectures(LectureService lectureService, Course course) {

        switch (choiceAction()) {
            case 1:
                String question = "would you create a new lecture? Y - Yes N - No";
                System.out.println(question);
                String userChoice = readAndValidationInput("[y|Y|n|N]");

                while (userChoice.equalsIgnoreCase("Y")) {

                    System.out.println("input teacher's id ");
                    System.out.println("available teacher's id " + Arrays.toString(personService.getAllTeacherIds()));

                    int teacherId = readInteger();// todo validation [1-9]

                    System.out.print("input name of lecture ");
                    String lectureName = readAndValidationInput("\\w+\\d*");

                    System.out.print("input description of lecture ");
                    String descriptionLecture = readAndValidationInput("\\w+");

                    lectureService.createLecture(course.getId(), lectureName, teacherId, descriptionLecture);

                    System.out.println(question);
                    userChoice = readAndValidationInput("[y|Y|n|N]");
                }
                System.out.println("Total created " + Lecture.counter + " lectures");
                break;
            case 2:
                System.out.println("open lecture number");
                int lectureId = readInteger();// todo validation [1-9]{2}
                lectureService.printById(lectureId);
                break;
            case 3:
                System.out.println("the list of lectures");
                lectureService.out();
                break;
            case 4:
                System.out.println("input lecture number");
                lectureId = readInteger();// todo validation
                lectureService.deleteById(lectureId);
                break;
            case 5:
                System.out.println("exit");
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    public void workWithPerson(PersonService personService, Course course) {
    }

    public void autoCreateLectures(LectureService lectureService, Course course, int personId) {
        String[] autoName = {"firstLecture", "secondLecture", "thirdLecture"};
        for (int i = 0; i < 2; i++) {
            lectureService.createLecture(course.getId(), autoName[i], personId, "descriptionLecture");
        }
    }

    public String validate(String data, String pattern) {
        boolean b = data.matches(pattern);
        while (!b) {
            System.out.println("error, repeat input");
            data = SCANNER.nextLine();
            b = data.matches(pattern);
        }
        return data;
    }

    public String readAndValidationInput(String pattern) {
        String name = SCANNER.nextLine();
        validate(name, pattern);
        return name;
    }

    public int indexIsValid(int index) {
        System.out.println("Error, index must be from 0 to ");
        int indexInput = SCANNER.nextInt();
        return indexInput;
    }
}

