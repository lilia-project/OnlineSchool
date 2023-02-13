package org.lilia.view;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.HomeworkDto;
import org.lilia.model.Homework;
import org.lilia.service.HomeworkService;
import org.lilia.service.LectureService;

import java.util.List;

public class HomeworkView {

    private final LectureService lectureService;

    public HomeworkView(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    String userChoice = "Y";

    public void workWithHomework(HomeworkService homeworkService) {

        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1:
                    while (userChoice.equalsIgnoreCase("Y")) {

                        ConsoleUtils.print(Constants.LECTURE_ID);
                        int lectureId = lectureService.lectureIdIsValid();
                        //
                        addNewHomeworkToLecture(homeworkService, lectureId);
                    }
                    break;
                case 2:
                    ConsoleUtils.print(Constants.HOMEWORK_ID);
                    int homeworkId = homeworkService.homeworkIdIsValid();
                    Homework homework = homeworkService.getRequireById(homeworkId);
                    print(homework);

                    System.out.println(Constants.EDIT_ELEMENT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

                    editHomework(homeworkService, homework);
                    break;
                case 3:
                    ConsoleUtils.print(Constants.LECTURE_ID);
                    int lectureId = lectureService.lectureIdIsValid();
                    List<Homework> allByLectureId = homeworkService.findAllByLectureId(lectureId);
                    System.out.println(allByLectureId);

                    ConsoleUtils.print(Constants.ACTION);
                    int action = ConsoleUtils.workWithListHomework();

                    switch (action) {
                        case 1:
                            while (userChoice.equalsIgnoreCase("Y")) {
                                addNewHomeworkToLecture(homeworkService, lectureId);
                            }
                            break;
                        case 2:
                            deleteHomework(homeworkService);
                            break;
                        case 3:
                            break;
                        default:
                            ConsoleUtils.print(Constants.ERROR);
                            break;
                    }
                    break;
                case 4:
                    deleteHomework(homeworkService);
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

            ConsoleUtils.print(Constants.DESCRIPTION);
            String additionalMaterial = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

            HomeworkDto homeworkDto = homeworkService.createHomeworkDto(task);
            System.out.println(homeworkDto);

            Homework homeworkUpdate = homeworkService.updateHomework(homework, homeworkDto);
            System.out.println(homeworkUpdate);

            ConsoleUtils.print(Constants.EDIT_ELEMENT);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
    }

    private void addNewHomeworkToLecture(HomeworkService homeworkService, int lectureId) {

        ConsoleUtils.print(Constants.TASK);
        String task = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

        homeworkService.createHomework(lectureId, task);

        System.out.println(Constants.CREATE_NEW);
        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
    }

    private void print(Homework homework) {
        System.out.println(homework);
    }
}
