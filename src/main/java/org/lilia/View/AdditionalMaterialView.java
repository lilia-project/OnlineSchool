package org.lilia.View;

import org.lilia.ConsoleUtils;
import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.models.AdditionalMaterial;
import org.lilia.service.AdditionalMaterialService;

public class AdditionalMaterialView {
    public void workWithAdditionalMaterials(AdditionalMaterialService additionalMaterialService) {
        String questionToUser;
        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1:
                    questionToUser = "would you create a new additionalMaterial? Y - Yes N - No";

                    while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.print("input name of additionalMaterial ");
                        String additionalMaterialName = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        additionalMaterialService.createAdditionalMaterial(additionalMaterialName);

                        System.out.println(questionToUser);
                        userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
                    }
                    break;
                case 2:
                    questionToUser = "would you edit an additionalMaterial?\n Y - yes, N - no";

                    System.out.println("array have " + additionalMaterialService.size() + " additionalMaterials");
                    System.out.println("select additionalMaterial's id");

                    //int lectureId = additionalMaterialService.lectureIdIsValid();
                    int additionalMaterialId = ConsoleUtils.readInteger();
                    AdditionalMaterial additionalMaterial;
                    try {
                        additionalMaterial = additionalMaterialService.printAndGetById(additionalMaterialId);
                    } catch (NoSuchMaterialIdException e) {
                        System.out.println("additionalMaterial was not found by id " + additionalMaterialId);
                        throw new RuntimeException(e);
                    }

                    System.out.println(questionToUser);
                    userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");

                   /* while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.println("lecture's name");
                        String lectureName = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        System.out.println("lecture's description");
                        String lectureDescription = ConsoleUtils.readAndValidationInput("\\w+\\d*");

                        System.out.println("choose from available course id");
                        String courseId = ConsoleUtils.readAndValidationInput("\\d+");

                        System.out.println("teacher's id = ");
                        String personId = ConsoleUtils.readAndValidationInput("\\d+");

                        LectureDto lectureDto = additionalMaterialService.createLectureDto(Integer.parseInt(courseId), lectureName, lectureDescription, Integer.parseInt(personId));
                        System.out.println(lectureDto);

                        Lecture lectureUpdate = additionalMaterialService.updateLecture(lecture, lectureDto);
                        System.out.println(lectureUpdate);

                        System.out.println(questionToUser);
                        userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
                    }*/
                    break;
                case 3:
                    System.out.println("the list of additionalMaterials");
                    additionalMaterialService.out();
                    break;
                case 4:
                    System.out.println("input additionalMaterial's id");
                    additionalMaterialId = additionalMaterialService.additionalMaterialIdIsValid();
                    int index = additionalMaterialService.getIndexById(additionalMaterialId);
                    additionalMaterialService.deleteByIndex(index);
                    System.out.println("additionalMaterial had been delete");
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
