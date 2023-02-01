package org.lilia.view;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.model.AdditionalMaterial;
import org.lilia.dto.AdditionalMaterialDto;
import org.lilia.model.ResourceType;
import org.lilia.service.AdditionalMaterialService;
import org.lilia.service.LectureService;

public class AdditionalMaterialView {

    private final LectureService lectureService;

    public AdditionalMaterialView(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public void workWithAdditionalMaterials(AdditionalMaterialService additionalMaterialService) {
        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1:
                    while (userChoice.equalsIgnoreCase("Y")) {

                        System.out.print("input name of additionalMaterial ");
                        String additionalMaterialName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        additionalMaterialService.createAdditionalMaterial(additionalMaterialName);

                        ConsoleUtils.print(Constants.CREATE_NEW);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    break;
                case 2:
                    System.out.println("select additionalMaterial's id");

                    int additionalMaterialId = additionalMaterialService.additionalMaterialIdIsValid();
                    AdditionalMaterial additionalMaterial = additionalMaterialService.getRequireById(additionalMaterialId);
                    print(additionalMaterial);

                    ConsoleUtils.print(Constants.EDIT);
                    while (userChoice.equalsIgnoreCase("Y")) {
                        System.out.println("additionalMaterial's name");
                        String name = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        System.out.println("lecture's id");
                        int lectureId = lectureService.lectureIdIsValid();

                        System.out.println("resourceType's description");
                        ResourceType resourceType = additionalMaterialService.getValueOfEnum();// todo enum

                        AdditionalMaterialDto additionalMaterialDto = additionalMaterialService.createAdditionalMaterialDto(lectureId, name, resourceType);
                        AdditionalMaterial additionalMaterialUpdate = additionalMaterialService.updateAdditionalMaterial(additionalMaterial, additionalMaterialDto);
                        System.out.println(additionalMaterialUpdate);

                        ConsoleUtils.print(Constants.EDIT);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    break;
                case 3:
                    additionalMaterialService.getAll();
                    break;
                case 4:
                    System.out.println("input additionalMaterial's id");
                    additionalMaterialId = additionalMaterialService.additionalMaterialIdIsValid();
                    additionalMaterialService.deleteById(additionalMaterialId);
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

    private void print(AdditionalMaterial additionalMaterial) {
        System.out.println(additionalMaterial);
    }
}
