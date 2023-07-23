package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.dto.AdditionalMaterialDto;
import org.lilia.entity.AdditionalMaterial;
import org.lilia.entity.ResourceType;
import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.repository.AdditionalMaterialRepository;
import org.lilia.repository.ConnectionFactory;
import org.lilia.util.ConsoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AdditionalMaterialService extends ConnectionFactory {
    private final AdditionalMaterialRepository additionalMaterialRepository;

    @Autowired
    public AdditionalMaterialService(AdditionalMaterialRepository additionalMaterialRepository) {
        this.additionalMaterialRepository = additionalMaterialRepository;
    }

    public Boolean isCreateAdditionalMaterial(String name, int lectureId) {
        AdditionalMaterial additionalMaterial = new AdditionalMaterial(name, lectureId);
        return AdditionalMaterialRepository.save(additionalMaterial);
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

    public void getAll(AdditionalMaterial.SortField sortField, int lectureId) {
        additionalMaterialRepository.getAll(sortField, lectureId);
    }

    public void deleteById(int additionalMaterialId) {
        Optional<AdditionalMaterial> additionalMaterial = additionalMaterialRepository.get(additionalMaterialId);
        if (additionalMaterial.isEmpty()) {
            throw new NoSuchMaterialIdException(additionalMaterialId);
        }
        additionalMaterialRepository.delete(additionalMaterial.get());
    }

    public int additionalMaterialIdIsValid() {
        int additionalMaterialId = ConsoleUtils.readInteger();
        Optional<AdditionalMaterial> additionalMaterial = additionalMaterialRepository.get(additionalMaterialId);
        while (additionalMaterial.isEmpty()) {
            ConsoleUtils.print(Constants.ELEMENT_NOT_EXIST);
            additionalMaterialId = ConsoleUtils.readInteger();
            additionalMaterial = additionalMaterialRepository.get(additionalMaterialId);
        }
        return additionalMaterialId;
    }

    public AdditionalMaterial getRequireById(int additionalMaterialId) {
        Optional<AdditionalMaterial> additionalMaterial = additionalMaterialRepository.get(additionalMaterialId);
        if (additionalMaterial.isEmpty()) {
            throw new NoSuchMaterialIdException(additionalMaterialId);
        }
        return additionalMaterial.get();
    }

    public AdditionalMaterial updateAdditionalMaterial(AdditionalMaterial additionalMaterial, AdditionalMaterialDto additionalMaterialDto) {
        if (!(additionalMaterialDto.getName()).isEmpty()) {
            additionalMaterial.setName(additionalMaterialDto.getName());
        }
        if (!(additionalMaterialDto.getResourceType()).equals(additionalMaterial.getResourceType())) {
            additionalMaterial.setResourceType(additionalMaterialDto.getResourceType());
        }
        if (additionalMaterialDto.getLectureId() != 0) {
            additionalMaterial.setLectureId(additionalMaterialDto.getLectureId());
        }
        return additionalMaterial;
    }

    public List<AdditionalMaterial> findAllByLectureId(int lectureId) {
        return additionalMaterialRepository.getByLectureId(lectureId).orElse(Collections.emptyList());
    }

    public void backupMaterial() {
        additionalMaterialRepository.serializeMaterial();
    }

    public void deserialize() {
        additionalMaterialRepository.deserializeMaterial();
    }

    public void printAllWithGroupingByLectureId() {
        additionalMaterialRepository.printAddMaterialsGroupingByLectureId();
    }
}
