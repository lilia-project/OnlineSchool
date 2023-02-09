package org.lilia.view;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.HomeworkDto;
import org.lilia.model.Homework;
import org.lilia.service.HomeworkService;
import org.lilia.service.LectureService;

public class HomeworkView {

    private final LectureService lectureService;

    public HomeworkView(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public void workWithHomework(HomeworkService homeworkService) {
        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1:
                    while (userChoice.equalsIgnoreCase("Y")) {
                        ConsoleUtils.print(Constants.LECTURE_ID);
                        int lectureId = lectureService.lectureIdIsValid();

                        ConsoleUtils.print(Constants.NAME);
                        String task = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        System.out.println("input additionalMaterials");
                        String additionalMaterial = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        homeworkService.createHomework(lectureId, task, additionalMaterial);
                        System.out.println(Constants.CREATE_NEW);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    break;
                case 2:
                    ConsoleUtils.print(Constants.HOMEWORK_ID);
                    int homeworkId = homeworkService.homeworkIdIsValid();
                    Homework homework = homeworkService.getRequireById(homeworkId);

                    System.out.println(Constants.EDIT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

                    while (userChoice.equalsIgnoreCase("Y")) {

                        ConsoleUtils.print(Constants.NAME);
                        String task = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        ConsoleUtils.print(Constants.DESCRIPTION);
                        String additionalMaterial = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        HomeworkDto homeworkDto = homeworkService.createHomeworkDto(task, additionalMaterial);
                        System.out.println(homeworkDto);

                        Homework homeworkUpdate = homeworkService.updateHomework(homework, homeworkDto);
                        System.out.println(homeworkUpdate);

                        ConsoleUtils.print(Constants.EDIT);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    break;
                case 3:
                    ConsoleUtils.print(Constants.LECTURE_ID);
                    int lectureId = lectureService.lectureIdIsValid();
                    homeworkService.findAllByLectureId(lectureId);
                    break;
                case 4:
                    ConsoleUtils.print(Constants.HOMEWORK_ID);
                    homeworkId = homeworkService.homeworkIdIsValid();
                    homeworkService.deleteById(homeworkId);
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
