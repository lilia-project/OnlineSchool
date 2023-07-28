package org.lilia.view;

import org.lilia.constant.Constants;
import org.lilia.dto.AdditionalMaterialDto;
import org.lilia.entity.AdditionalMaterial;
import org.lilia.entity.ResourceType;
import org.lilia.service.AdditionalMaterialService;
import org.lilia.service.LectureService;
import org.lilia.util.ConsoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdditionalMaterialView {

    private final LectureService lectureService;
    String userChoice = "Y";

    @Autowired
    public AdditionalMaterialView(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public void workWithAdditionalMaterials(AdditionalMaterialService additionalMaterialService) {

        while (userChoice.equalsIgnoreCase("Y")) {

            switch (ConsoleUtils.choiceActionForAddMaterial()) {
                case 1 -> {
                    while (userChoice.equalsIgnoreCase("Y")) {

                        createNew(additionalMaterialService);

                        ConsoleUtils.print(Constants.CREATE_NEW);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                }
                case 2 -> {
                    AdditionalMaterial additionalMaterial = getAdditionalMaterial(additionalMaterialService);
                    ConsoleUtils.print(Constants.ELEMENT_EDIT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    editAdditionMaterial(additionalMaterialService, additionalMaterial);
                }
                case 3 -> {
                    int lectureId = getAllByLectureId(additionalMaterialService);
                    ConsoleUtils.print(Constants.ACTION);
                    int action = ConsoleUtils.workWithListAddMaterial();
                    switch (action) {
                        case 1:
                            while (userChoice.equalsIgnoreCase("Y")) {
                                addNewMaterialToLecture(additionalMaterialService, lectureId);
                            }
                            break;
                        case 2:
                            deleteAdditionalMaterial(additionalMaterialService);
                            break;
                        case 3:
                            sortMaterial(additionalMaterialService, lectureId);
                            break;
                        case 0:
                            break;
                        default:
                            ConsoleUtils.print(Constants.ERROR);
                            break;
                    }
                }
                case 4 -> deleteAdditionalMaterial(additionalMaterialService);
                case 5 -> {
                    additionalMaterialService.serializeMaterial();
                    System.out.println("Backup created");
                }
                case 6 -> additionalMaterialService.deserializeMaterial();
                case 7 -> additionalMaterialService.printAllWithGroupingByLectureId().forEach(System.out::println);
                case 8 -> ConsoleUtils.print(Constants.EXIT);
                default -> ConsoleUtils.print(Constants.ERROR);
            }
            ConsoleUtils.print(Constants.STAY_IN);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
    }

    private int getAllByLectureId(AdditionalMaterialService additionalMaterialService) {
        ConsoleUtils.print(Constants.LECTURE_ID);
        int lectureId = lectureService.lectureIdIsValid();
        if (additionalMaterialService.findAllByLectureId(lectureId).isPresent()) {
            additionalMaterialService.findAllByLectureId(lectureId).get();
        }
        return lectureId;
    }

    private AdditionalMaterial getAdditionalMaterial(AdditionalMaterialService additionalMaterialService) {
        ConsoleUtils.print(Constants.MATERIAL_ID);

        int additionalMaterialId = additionalMaterialService.additionalMaterialIdIsValid();
        AdditionalMaterial additionalMaterial = additionalMaterialService.getRequireById(additionalMaterialId);
        print(additionalMaterial);
        return additionalMaterial;
    }

    private void createNew(AdditionalMaterialService additionalMaterialService) {
        ConsoleUtils.print(Constants.LECTURE_ID);
        int lectureId = lectureService.lectureIdIsValid();

        addNewMaterialToLecture(additionalMaterialService, lectureId);
    }

    private void editAdditionMaterial(AdditionalMaterialService additionalMaterialService, AdditionalMaterial additionalMaterial) {
        while (userChoice.equalsIgnoreCase("Y")) {
            ConsoleUtils.print(Constants.NAME);
            String name = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

            ConsoleUtils.print(Constants.LECTURE_ID);
            int lectureId = lectureService.lectureIdIsValid();

            ConsoleUtils.print(Constants.RESOURCE_TYPE);
            String parameter = ConsoleUtils.choiceParameterResource();
            ResourceType resourceType = ResourceType.valueOf(parameter);

            AdditionalMaterialDto additionalMaterialDto = additionalMaterialService.createAdditionalMaterialDto(lectureId, name, resourceType);
            additionalMaterialService.updateAdditionalMaterial(additionalMaterial, additionalMaterialDto);

            ConsoleUtils.print(Constants.ELEMENT_EDIT);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
    }

    private void deleteAdditionalMaterial(AdditionalMaterialService additionalMaterialService) {
        ConsoleUtils.print(Constants.MATERIAL_ID);
        int additionalMaterialId = additionalMaterialService.additionalMaterialIdIsValid();
        additionalMaterialService.deleteById(additionalMaterialId);
    }

    private void sortMaterial(AdditionalMaterialService additionalMaterialService, int lectureId) {

        List<AdditionalMaterial> materials = additionalMaterialService.getAllBySortFieldAndLectureId(AdditionalMaterial.SortField.ID, lectureId);
        materials.forEach(System.out::println);

        userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {

            ConsoleUtils.print("by other parameter " + Constants.APPLY_SORT);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

            if (userChoice.equalsIgnoreCase("Y")) {
                ConsoleUtils.print(Constants.SELECT_PARAMETER_SORT);

                int sortChoice = ConsoleUtils.choiceParameterSort();
                AdditionalMaterial.SortField field = AdditionalMaterial.SortField.getById(sortChoice);

                additionalMaterialService.getAllBySortFieldAndLectureId(field, lectureId);
            }
        }
    }

    private void addNewMaterialToLecture(AdditionalMaterialService additionalMaterialService, int lectureId) {

        ConsoleUtils.print(Constants.NAME);
        String additionalMaterialName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

        additionalMaterialService.createNewAddMaterial(additionalMaterialName, lectureId);
    }

    private void print(AdditionalMaterial additionalMaterial) {
        System.out.println(additionalMaterial);
    }
}
