package org.lilia.View;

import org.lilia.ConsoleUtils;
import org.lilia.exception.NoSuchLectureIdException;
import org.lilia.models.Lecture;
import org.lilia.models.LectureDto;
import org.lilia.service.LectureService;

public class LectureView {

    public void workWithLectures(LectureService lectureService) {
        String questionToUser;
        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1:
                    questionToUser = "would you create a new lecture? Y - Yes N - No";

                    while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.print("input name of lecture ");
                        String lectureName = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        lectureService.createLecture(lectureName);

                        System.out.println(questionToUser);
                        userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
                    }
                    System.out.println("You created " + Lecture.counter + " new lecture(s)");
                    break;
                case 2:
                    questionToUser = "would you edit a lecture?\n Y - yes, N - no";

                    System.out.println("array have " + lectureService.size() + " lectures");
                    System.out.println("select lecture's id");

                    //int lectureId = lectureService.lectureIdIsValid(); 
                    int lectureId = ConsoleUtils.readInteger();
                    Lecture lecture;
                    try {
                        lecture = lectureService.printAndGetById(lectureId);
                    } catch (NoSuchLectureIdException e) {
                        System.out.println("lecture was not found by id " + lectureId);
                        throw new RuntimeException(e);
                    }

                    System.out.println(questionToUser);
                    userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");

                    while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.println("lecture's name");
                        String lectureName = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        System.out.println("lecture's description");
                        String lectureDescription = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        System.out.println("choose from available course id");
                        String courseId = ConsoleUtils.readAndValidationInput("\\d+");

                        System.out.println("teacher's id = ");
                        String personId = ConsoleUtils.readAndValidationInput("\\d+");

                        LectureDto lectureDto = lectureService.createLectureDto(Integer.parseInt(courseId), lectureName, lectureDescription, Integer.parseInt(personId));
                        System.out.println(lectureDto);

                        Lecture lectureUpdate = lectureService.updateLecture(lecture, lectureDto);
                        System.out.println(lectureUpdate);

                        System.out.println(questionToUser);
                        userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
                    }
                    break;
                case 3:
                    System.out.println("the list of lectures");
                    lectureService.out();
                    break;
                case 4:
                    System.out.println("input lecture's id");
                    lectureId = lectureService.lectureIdIsValid();
                    lectureService.deleteById(lectureId);
                    break;
                case 5:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
            System.out.println("stay in category? Y - yes, N - no");
            userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
            if (userChoice.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}
