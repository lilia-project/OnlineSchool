package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.AdditionalMaterialDto;
import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.model.AdditionalMaterial;
import org.lilia.model.ResourceType;
import org.lilia.repository.AdditionalMaterialRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AdditionalMaterialService {
    private final AdditionalMaterialRepository additionalMaterialRepository;

    public AdditionalMaterialService(AdditionalMaterialRepository additionalMaterialRepository) {
        this.additionalMaterialRepository = additionalMaterialRepository;
    }

    public void createAdditionalMaterial(String name) {
        AdditionalMaterial additionalMaterial = new AdditionalMaterial(name);
        additionalMaterialRepository.add(additionalMaterial);
        System.out.println("\nthe additionalMaterial has been created: " + additionalMaterial);
    }

    public AdditionalMaterialDto createAdditionalMaterialDto(int lectureId, String name, ResourceType resourceType) {

        if (resourceType.getParameter() == 1) {
            return new AdditionalMaterialDto(lectureId, name, ResourceType.URL);
        }
        if (resourceType.getParameter() == 2) {
            return new AdditionalMaterialDto(lectureId, name, ResourceType.VIDEO);
        } else
            return new AdditionalMaterialDto(lectureId, name, ResourceType.BOOK);
    }

    public void getAll() {
        additionalMaterialRepository.getAll();
    }

   /* public void getAll(AdditionalMaterial.SortField sortField) {
        additionalMaterialRepository.getAll(sortField);
    }*/

    public void deleteById(int additionalMaterialId) {
        Optional<AdditionalMaterial> additionalMaterial = additionalMaterialRepository.getById(additionalMaterialId);
        if (additionalMaterial.isEmpty()) {
            throw new NoSuchMaterialIdException(additionalMaterialId);
        }
        additionalMaterialRepository.remove(additionalMaterial.get());
        ConsoleUtils.print(Constants.ELEMENT_DELETED);
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

    public AdditionalMaterial updateAdditionalMaterial(AdditionalMaterial additionalMaterial, AdditionalMaterialDto additionalMaterialDto) {
        if ((additionalMaterialDto.getName()) != null) {
            additionalMaterial.setName(additionalMaterialDto.getName());
        }
        if (additionalMaterialDto.getResourceType() != null) {
            additionalMaterial.setResourceType(additionalMaterialDto.getResourceType());
        }
        if (additionalMaterialDto.getLectureId() != 0) {
            additionalMaterial.setLectureId(additionalMaterialDto.getLectureId());
        }
        return additionalMaterial;
    }

    public List<AdditionalMaterial> findAllByLectureId(int lectureId) {
        Optional<List<AdditionalMaterial>> byLectureId = additionalMaterialRepository.getByLectureId(lectureId);
        if (byLectureId.isPresent()) {
            return byLectureId.get();
        }
        return Collections.emptyList();
    }
}
