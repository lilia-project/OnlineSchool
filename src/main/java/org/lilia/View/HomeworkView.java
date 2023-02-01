package org.lilia.View;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.model.Homework;
import org.lilia.model.HomeworkDto;
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
                        System.out.println("input lecture's id");
                        int lectureId = lectureService.lectureIdIsValid();

                        System.out.println("input task's name");
                        String task = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        System.out.println("input additionalMaterials");
                        String additionalMaterial = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        homeworkService.createHomework(lectureId, task, additionalMaterial);
                        System.out.println(Constants.CREATE_NEW);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    System.out.println("You created " + Homework.getCounter() + " new homework(s)");
                    break;
                case 2:
                    System.out.println("select homework's id");

                    int homeworkId = homeworkService.homeworkIdIsValid();// todo вывести все лекции из репо, чтобы выбрать id лекции
                    Homework homework = homeworkService.getById(homeworkId);

                    System.out.println(Constants.EDIT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

                    while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.println("homework's name");
                        String task = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        System.out.println("homework's description");
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
                    System.out.println("the list of homework");
                    lectureService.out();
                    break;
                case 4:
                    System.out.println("input homework's id");
                    homeworkId = homeworkService.homeworkIdIsValid();
                    homeworkService.deleteById(homeworkId);
                    break;
                case 5:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("Error");
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
