package org.lilia.service;

import org.lilia.models.Lecture;
import org.lilia.models.LectureDto;

import static org.lilia.Main.SCANNER;

public class MainService {

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

        return Integer.parseInt(readAndValidationInput("[1-5]"));
    }

    public int choiceAction() {
        System.out.println("1 - create new");
        System.out.println("2 - open/edit");
        System.out.println("3 - output all");
        System.out.println("4 - delete");
        System.out.println("5 - exit");

        return Integer.parseInt(readAndValidationInput("[1-5]"));
    }

    public void workWithCourse(CourseService courseService) {
    }

    public void workWithLectures(LectureService lectureService) {
        String questionToUser;
        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (choiceAction()) {
                case 1:
                    questionToUser = "would you create a new lecture? Y - Yes N - No";

                    while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.print("input name of lecture ");
                        String lectureName = readAndValidationInput("\\w+\\d*");

                        lectureService.createLecture(0, lectureName, null, 0);

                        System.out.println(questionToUser);
                        userChoice = readAndValidationInput("[y|Y|n|N]");
                    }
                    System.out.println("You created " + Lecture.counter + " new lecture(s)");
                    break;
                case 2:
                    questionToUser = "would you edit a lecture?\n Y - yes, N - no";

                    System.out.println("array have " + lectureService.size() + " lectures");
                    System.out.println("select number of lecture");

                    int lectureId = indexIsValid(); // todo вывести все лекции из репо, чтобы выбрать id лекции
                    Lecture lecture = lectureService.getById(lectureId);

                    System.out.println(questionToUser);
                    userChoice = readAndValidationInput("[y|Y|n|N]");

                    while (userChoice.equalsIgnoreCase("Y")) {
                        System.out.println("lecture's name");
                        String lectureName = readAndValidationInput("\\w+\\d*");
                        System.out.println("lecture's description");
                        String lectureDescription = readAndValidationInput("\\w+\\d*");
                        System.out.println("choose from available course id");

                        String courseId = readAndValidationInput("\\d+");
                        System.out.println("teacher id");
                        String personId = readAndValidationInput("\\d+");

                        LectureDto lectureDto = lectureService.createLectureDto(Integer.parseInt(courseId), lectureName, lectureDescription, Integer.parseInt(personId));
                        System.out.println(lectureDto);

                        Lecture lectureUpdate = lectureService.updateLecture(lecture, lectureDto);
                        System.out.println(lectureUpdate);

                        System.out.println(questionToUser);
                        userChoice = readAndValidationInput("[y|Y|n|N]");
                    }
                    break;
                case 3:
                    System.out.println("the list of lectures");
                    lectureService.out();
                    break;
                case 4:
                    System.out.println("input lecture number");
                    lectureId = indexIsValid();
                    lectureService.deleteById(lectureId);
                    break;
                case 5:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
            System.out.println("stay in category Lecture?");
            userChoice = readAndValidationInput("[y|Y|n|N]");
            if (userChoice.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    public void workWithPerson(PersonService personService) {
    }

    public String readAndValidationInput(String pattern) {
        String name = SCANNER.nextLine();
        validate(name, pattern);
        return name;
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

    public int indexIsValid() {
        int index = readInteger();
        if (index > lectureService.size() || index < 1) {
            System.out.println("Error, index must be from 1 to " + lectureService.size());
            System.out.println("repeat input");
            index = readInteger();
        }
        return index;
    }
}

