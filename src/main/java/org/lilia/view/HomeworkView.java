package org.lilia.view;

import org.lilia.constant.Constants;
import org.lilia.dto.HomeworkDto;
import org.lilia.log.Logger;
import org.lilia.log.LoggerFactory;
import org.lilia.model.Homework;
import org.lilia.service.HomeworkService;
import org.lilia.service.LectureService;
import org.lilia.util.ConsoleUtils;

public class HomeworkView {

    private static final Logger logger = LoggerFactory.getLogger(HomeworkView.class);
    private static String userChoice = "Y";
    private final LectureService lectureService;

    public HomeworkView(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public void workWithHomework(HomeworkService homeworkService) {
        logger.info("work with homework section");
        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1 -> {
                    while (userChoice.equalsIgnoreCase("Y")) {

                        logger.info("selected to create homework");

                        createNewHomework(homeworkService);

                        logger.info("homework created successful");
                    }
                }
                case 2 -> {
                    logger.info("selected to get homework by id");
                    Homework homework = getHomeworkById(homeworkService);
                    ConsoleUtils.print(Constants.ELEMENT_EDIT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    while (userChoice.equalsIgnoreCase("Y")) {

                        editHomework(homeworkService, homework);

                        ConsoleUtils.print(Constants.ELEMENT_EDIT);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                }
                case 3 -> {
                    logger.info("selected to get all homeworks by lecture id");
                    int lectureId = outputAllByLectureId(homeworkService);
                    ConsoleUtils.print(Constants.ACTION);
                    int action = ConsoleUtils.workWithListHomework();
                    switch (action) {
                        case 1 -> addNewHomework(homeworkService, lectureId);
                        case 2 -> deleteHomework(homeworkService);
                        case 3 -> logger.info("selected EXIT from menu");
                        default -> {
                            logger.error(Constants.ERROR);
                            ConsoleUtils.print(Constants.ERROR);
                        }
                    }
                }
                case 4 -> {
                    logger.info("selected delete homework");
                    deleteHomework(homeworkService);
                    logger.info("homework deleted successful");
                }
                case 5 -> homeworkService.backupHomework();
                case 6 -> homeworkService.deserialization();
                case 7 -> {
                    logger.info("selected EXIT");
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

    private int outputAllByLectureId(HomeworkService homeworkService) {
        ConsoleUtils.print(Constants.LECTURE_ID);
        int lectureId = lectureService.lectureIdIsValid();
        homeworkService.findAllByLectureId(lectureId).ifPresent(i -> System.out.println());
        return lectureId;
    }

    private void createNewHomework(HomeworkService homeworkService) {
        ConsoleUtils.print(Constants.LECTURE_ID);
        int lectureId = lectureService.lectureIdIsValid();
        addNewHomeworkToLecture(homeworkService, lectureId);
    }

    private Homework getHomeworkById(HomeworkService homeworkService) {
        ConsoleUtils.print(Constants.HOMEWORK_ID);
        int homeworkId = homeworkService.homeworkIdIsValid();
        Homework homework = homeworkService.getRequireById(homeworkId);
        print(homework);
        return homework;
    }

    private void addNewHomework(HomeworkService homeworkService, int lectureId) {
        while (userChoice.equalsIgnoreCase("Y")) {
            addNewHomeworkToLecture(homeworkService, lectureId);
        }
    }

    private void deleteHomework(HomeworkService homeworkService) {
        ConsoleUtils.print(Constants.HOMEWORK_ID);
        int homeworkId = homeworkService.homeworkIdIsValid();
        homeworkService.deleteById(homeworkId);
    }


    private void editHomework(HomeworkService homeworkService, Homework homework) {
        while (userChoice.equalsIgnoreCase("Y")) {

            ConsoleUtils.print(Constants.TASK);
            String task = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

            HomeworkDto homeworkDto = homeworkService.createHomeworkDto(task);
            System.out.println(homeworkDto);

            Homework homeworkUpdate = homeworkService.updateHomework(homework, homeworkDto);
            System.out.println(homeworkUpdate);

            ConsoleUtils.print(Constants.ELEMENT_EDIT);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
    }

    private void addNewHomeworkToLecture(HomeworkService homeworkService, int lectureId) {

        ConsoleUtils.print(Constants.TASK);
        String task = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

        homeworkService.createHomework(lectureId, task);
        logger.info(Constants.ELEMENT_CREATED);

        ConsoleUtils.print(Constants.CREATE_NEW);
        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
    }

    private void print(Homework homework) {
        System.out.println(homework);
    }
}
