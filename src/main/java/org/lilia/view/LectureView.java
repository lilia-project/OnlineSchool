package org.lilia.view;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.model.Lecture;
import org.lilia.dto.LectureDto;
import org.lilia.service.LectureService;

public class LectureView {

    public void workWithLectures(LectureService lectureService) {

        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1:

                    while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.print("input name of lecture ");
                        String lectureName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        lectureService.createLecture(lectureName);

                        System.out.println(Constants.CREATE_NEW);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    System.out.println("You created " + Lecture.counter + " new lecture(s)");
                    break;
                case 2:
                    System.out.println("select lecture's id");
                    int lectureId = ConsoleUtils.readInteger();

                    Lecture lecture = lectureService.getRequireById(lectureId);
                    System.out.println(Constants.EDIT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

                    while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.println("lecture's name");
                        String lectureName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        System.out.println("lecture's description");
                        String lectureDescription = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        System.out.println("choose from available course id");
                        String courseId = ConsoleUtils.readAndValidationInput("\\d+");

                        System.out.println("teacher's id = ");
                        String personId = ConsoleUtils.readAndValidationInput("\\d+");

                        LectureDto lectureDto = lectureService.createLectureDto(Integer.parseInt(courseId), lectureName, lectureDescription, Integer.parseInt(personId));

                        Lecture lectureUpdate = lectureService.updateLecture(lecture, lectureDto);
                        System.out.println(lectureUpdate);

                        System.out.println(Constants.EDIT);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
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
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
            if (userChoice.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}
