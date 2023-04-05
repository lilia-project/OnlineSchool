package org.lilia.view;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.LectureDto;
import org.lilia.log.Logger;
import org.lilia.log.LoggerFactory;
import org.lilia.model.Lecture;
import org.lilia.service.LectureService;

public class LectureView {
    private static final Logger logger = LoggerFactory.getLogger(LectureView.class);

    public void workWithLectures(LectureService lectureService) {
        logger.info("work with lecture section");

        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1 -> {
                    logger.info("selected to create lecture");
                    while (userChoice.equalsIgnoreCase("Y")) {

                        Lecture lecture = createNewLecture(lectureService);
                        logger.info("lecture created successful" + lecture.getId());

                        ConsoleUtils.print(Constants.CREATE_NEW);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                }
                case 2 -> {
                    Lecture lecture = getLectureById(lectureService);
                    logger.info("found lecture " + lecture);
                    ConsoleUtils.print(Constants.ELEMENT_EDIT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    while (userChoice.equalsIgnoreCase("Y")) {

                        editLecture(lectureService, lecture);

                        ConsoleUtils.print(Constants.ELEMENT_EDIT);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                }
                case 3 -> lectureService.outputAll();
                case 4 -> {
                    logger.info("selected delete lecture");
                    deleteLecture(lectureService);
                    logger.info("lecture deleted successful");
                }
                case 5 -> lectureService.backupLecture();
                case 6 -> lectureService.deserialize();
                case 7 -> {
                    logger.info("selected EXIT from menu");
                    ConsoleUtils.print(Constants.EXIT);
                }
                default -> {
                    logger.error(Constants.ERROR);
                    ConsoleUtils.print(Constants.ERROR);
                }
            }
            ConsoleUtils.print(Constants.STAY_IN);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
    }

    private static void editLecture(LectureService lectureService, Lecture lecture) {
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
    }

    private static Lecture getLectureById(LectureService lectureService) {
        ConsoleUtils.print(Constants.LECTURE_ID);
        int lectureId = ConsoleUtils.readInteger();
        return lectureService.getRequireById(lectureId);
    }

    private static Lecture createNewLecture(LectureService lectureService) {
        ConsoleUtils.print(Constants.NAME);
        String lectureName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);
        return lectureService.createLecture(lectureName);
    }

    private static void deleteLecture(LectureService lectureService) {
        ConsoleUtils.print(Constants.LECTURE_ID);
        int lectureId = lectureService.lectureIdIsValid();
        lectureService.deleteById(lectureId);
    }
}
