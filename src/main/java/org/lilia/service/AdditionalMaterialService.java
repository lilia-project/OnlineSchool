package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.models.AdditionalMaterial;
import org.lilia.repository.AdditionalMaterialRepository;

import java.util.Optional;

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

    public void getAll() {
        additionalMaterialRepository.getAll();
    }

    public void deleteById(int additionalMaterialId) {
        Optional<AdditionalMaterial> additionalMaterial = additionalMaterialRepository.getById(additionalMaterialId);
        if (additionalMaterial.isEmpty()) {
            throw new NoSuchMaterialIdException(additionalMaterialId);
        }
        additionalMaterialRepository.remove(additionalMaterial.get());
        System.out.println("additional material has been deleted");
    }

    public int additionalMaterialIdIsValid() {
        int additionalMaterialId = ConsoleUtils.readInteger();
        Optional<AdditionalMaterial> additionalMaterial = additionalMaterialRepository.getById(additionalMaterialId);
        while (additionalMaterial.isEmpty()) {
            System.out.println("additionalMaterial was not found by id " + additionalMaterialId + ", repeat input");
            additionalMaterialId = ConsoleUtils.readInteger();
            additionalMaterial = additionalMaterialRepository.getById(additionalMaterialId);
        }
        return additionalMaterialId;
    }

    public AdditionalMaterial getRequireById(int additionalMaterialId) {
        Optional<AdditionalMaterial> additionalMaterial = additionalMaterialRepository.getById(additionalMaterialId);
        if (additionalMaterial.isEmpty()) {
            throw new NoSuchMaterialIdException(additionalMaterialId);
        }
        return additionalMaterial.get();
    }

    public int size() {
        return additionalMaterialRepository.size();
    }
}
