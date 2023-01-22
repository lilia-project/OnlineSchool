package org.lilia.View;

import org.lilia.ConsoleUtils;
import org.lilia.models.Homework;
import org.lilia.models.HomeworkDto;
import org.lilia.service.HomeworkService;
import org.lilia.service.LectureService;

public class HomeworkView {

    private final LectureService lectureService;

    public HomeworkView(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public void workWithHomework(HomeworkService homeworkService) {
        String questionToUser;
        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1:
                    questionToUser = "would you create a new homework? Y - Yes N - No";

                    while (userChoice.equalsIgnoreCase("Y")) {
                        System.out.println("input lecture's id");
                        int lectureId = lectureService.lectureIdIsValid();

                        System.out.println("input task's name");
                        String task = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        System.out.println("input additionalMaterials");
                        String additionalMaterial = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        homeworkService.createHomework(lectureId, task, additionalMaterial);
                        System.out.println(questionToUser);
                        userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
                    }
                    System.out.println("You created " + Homework.getCounter() + " new homework(s)");
                    break;
                case 2:
                    questionToUser = "would you edit a homework?\n Y - yes, N - no";

                    System.out.println("array have " + homeworkService.size() + " homework");
                    System.out.println("select number of homework");

                    int homeworkId = homeworkService.homeworkIdIsValid();// todo вывести все лекции из репо, чтобы выбрать id лекции
                    Homework homework = homeworkService.getById(homeworkId);

                    System.out.println(questionToUser);
                    userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");

                    while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.println("homework's name");
                        String task = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        System.out.println("homework's description");
                        String additionalMaterial = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        HomeworkDto homeworkDto= homeworkService.createHomeworkDto(task, additionalMaterial);
                        System.out.println(homeworkDto);

                        Homework homeworkUpdate = homeworkService.updateHomework(homework, homeworkDto);
                        System.out.println(homeworkUpdate);

                        System.out.println(questionToUser);
                        userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
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
            System.out.println("stay in category? Y - yes, N - no");
            userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
            if (userChoice.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}
