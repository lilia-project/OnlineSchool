package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.models.AdditionalMaterial;
import org.lilia.repository.AdditionalMaterialRepository;

public class AdditionalMaterialService {
    private final AdditionalMaterialRepository additionalMaterialRepository;

    public AdditionalMaterialService(AdditionalMaterialRepository additionalMaterialRepository) {
        this.additionalMaterialRepository = additionalMaterialRepository;
    }

    public void createAdditionalMaterial(String name) {
        AdditionalMaterial additionalMaterial = new AdditionalMaterial(name);
        additionalMaterialRepository.getList().add(additionalMaterial);
        System.out.println("\nthe additionalMaterial has been created: " + additionalMaterial);
    }

    public void out() {
        for (AdditionalMaterial additionalMaterial : additionalMaterialRepository.getList()) {
            //addHomeworkIntoLecture(lecture);
            System.out.println(additionalMaterial);
        }
    }

    public void deleteById(AdditionalMaterial additionalMaterial) {
        int index = getIndexByAdditionalMaterial(additionalMaterial);
        additionalMaterialRepository.getList().remove(index);
        System.out.println("additional material has been deleted");
    }

    public int additionalMaterialIdIsValid() {
        int additionalMaterialId = ConsoleUtils.readInteger();
        for (AdditionalMaterial additionalMaterial : additionalMaterialRepository.getList()) {
            if (additionalMaterial.getId() == 0) {
                System.out.println("input valid additionalMaterial's id");
                additionalMaterialId = ConsoleUtils.readInteger();
            }
        }
        return additionalMaterialId;
    }

    public int getIndexByAdditionalMaterial(AdditionalMaterial additionalMaterial) {
        int index = 0;
        for (AdditionalMaterial list : additionalMaterialRepository.getList()) {
            if (list == additionalMaterial) {
                index = additionalMaterialRepository.getList().indexOf(additionalMaterial);
            }
        }
        return index;
    }

    public int getIndexById(int additionalMaterialId) {
        int index = 0;
        for (AdditionalMaterial list : additionalMaterialRepository.getList()) {
            if (list.getId() == additionalMaterialId) {
                index = additionalMaterialRepository.getList().indexOf(list);
            }
        }
        return index;
    }

    public void deleteByIndex(int index) {
        additionalMaterialRepository.getList().remove(index);
    }

    public AdditionalMaterial printAndGetById(int additionalMaterialId) throws NoSuchMaterialIdException {
        int index = getIndexById(additionalMaterialId);
        AdditionalMaterial additionalMaterial = additionalMaterialRepository.getList().get(index);
        if (additionalMaterial == null) {
            throw new NoSuchMaterialIdException(additionalMaterialId);
        }
        System.out.println(additionalMaterial);
        return additionalMaterial;
    }

    public int size() {
        return additionalMaterialRepository.getList().size();
    }
}
