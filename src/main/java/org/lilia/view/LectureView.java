package org.lilia.view;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.LectureDto;
import org.lilia.model.Lecture;
import org.lilia.service.LectureService;

public class LectureView {

    public void workWithLectures(LectureService lectureService) {

        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1:

                    while (userChoice.equalsIgnoreCase("Y")) {

                        ConsoleUtils.print(Constants.NAME);
                        String lectureName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        lectureService.createLecture(lectureName);

                        System.out.println(Constants.CREATE_NEW);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    break;
                case 2:
                    ConsoleUtils.print(Constants.LECTURE_ID);
                    int lectureId = ConsoleUtils.readInteger();

                    Lecture lecture = lectureService.getRequireById(lectureId);
                    System.out.println(Constants.EDIT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

                    while (userChoice.equalsIgnoreCase("Y")) {

                        ConsoleUtils.print(Constants.NAME);
                        String lectureName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        ConsoleUtils.print(Constants.DESCRIPTION);
                        String lectureDescription = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        System.out.println("choose from available course id");
                        String courseId = ConsoleUtils.readAndValidationInput(Constants.NUMBER);

                        ConsoleUtils.print(Constants.TEACHER_ID);
                        String personId = ConsoleUtils.readAndValidationInput(Constants.NUMBER);

                        LectureDto lectureDto = lectureService.createLectureDto(Integer.parseInt(courseId), lectureName, lectureDescription, Integer.parseInt(personId));

                        Lecture lectureUpdate = lectureService.updateLecture(lecture, lectureDto);
                        System.out.println(lectureUpdate);

                        ConsoleUtils.print(Constants.EDIT);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    break;
                case 3:
                    lectureService.out();
                    break;
                case 4:
                    ConsoleUtils.print(Constants.LECTURE_ID);
                    lectureId = lectureService.lectureIdIsValid();
                    lectureService.deleteById(lectureId);
                    break;
                case 5:
                    ConsoleUtils.print(Constants.EXIT);
                    break;
                default:
                    ConsoleUtils.print(Constants.ERROR);
                    break;
            }
            ConsoleUtils.print(Constants.STAY_IN);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
            if (userChoice.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}
