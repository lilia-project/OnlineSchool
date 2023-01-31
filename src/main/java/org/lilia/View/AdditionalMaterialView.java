package org.lilia.View;

import org.lilia.ConsoleUtils;
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
                    System.out.println("select additionalMaterial's id");

                    int additionalMaterialId = additionalMaterialService.additionalMaterialIdIsValid();
                    AdditionalMaterial additionalMaterial = additionalMaterialService.getRequireById(additionalMaterialId);
                    print(additionalMaterial);

                    System.out.println(questionToUser);
                    break;
                case 3:
                    System.out.println("the list of additionalMaterials");
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
            System.out.println("stay in category? Y - yes, N - no");
            userChoice = ConsoleUtils.readAndValidationInput("[y|Y|n|N]");
            if (userChoice.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    private void print(AdditionalMaterial additionalMaterial) {
        System.out.println(additionalMaterial);
    }
}
