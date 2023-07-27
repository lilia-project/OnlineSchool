package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.dto.AdditionalMaterialDto;
import org.lilia.entity.AdditionalMaterial;
import org.lilia.entity.ResourceType;
import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.repository.AdditionalMaterialRepo;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalMaterialService {
    private final AdditionalMaterialRepo additionalMaterialRepo;

    @Autowired
    public AdditionalMaterialService(AdditionalMaterialRepo additionalMaterialRepo) {
        this.additionalMaterialRepo = additionalMaterialRepo;
    }

    public void createNewAddMaterial(String name, int lectureId) {
        if (name == null | lectureId == 0) {
            throw new IllegalArgumentException("material name or lectureId is null");
        }
        AdditionalMaterial additionalMaterial = new AdditionalMaterial();
        additionalMaterial.setName(name);
        additionalMaterial.setLectureId(lectureId);
        additionalMaterialRepo.save(additionalMaterial);
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

    public List<AdditionalMaterial> getAllBySortFieldAndLectureId(AdditionalMaterial.SortField sortField, int lectureId) {
        return additionalMaterialRepo.getAllBySortFieldAndLectureId(sortField, lectureId);
    }


    public void deleteById(Integer id) {
        additionalMaterialRepo.deleteById(id);
    }

    public int additionalMaterialIdIsValid() {
        int additionalMaterialId = ConsoleUtils.readInteger();
        return getRequireById(additionalMaterialId).getId();
    }

    public AdditionalMaterial getRequireById(int additionalMaterialId) {
        Optional<AdditionalMaterial> additionalMaterial = additionalMaterialRepo.findById(additionalMaterialId);
        if (additionalMaterial.isEmpty()) {
            throw new NoSuchMaterialIdException(additionalMaterialId);
        }
        return additionalMaterial.get();
    }

    public void updateAdditionalMaterial(AdditionalMaterial additionalMaterial, AdditionalMaterialDto additionalMaterialDto) {
        if (!(additionalMaterialDto.getName()).isEmpty()) {
            additionalMaterial.setName(additionalMaterialDto.getName());
        }
        if (!(additionalMaterialDto.getResourceType()).equals(additionalMaterial.getResourceType())) {
            additionalMaterial.setResourceType(additionalMaterialDto.getResourceType());
        }
        if (additionalMaterialDto.getLectureId() != 0) {
            additionalMaterial.setLectureId(additionalMaterialDto.getLectureId());
        }
        additionalMaterialRepo.updateMaterial(additionalMaterial);
    }

    public Optional<List<AdditionalMaterial>> findAllByLectureId(int lectureId) {
        return additionalMaterialRepo.getByLectureId(lectureId);
    }

    public void serializeMaterial() {
        List<AdditionalMaterial> materials = additionalMaterialRepo.findAll();
        Serializer.serialize(materials, FilePath.FILE_PATH_ADDITION_MATERIAL);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }


    public void deserializeMaterial() {
        String filePath = FilePath.FILE_PATH_ADDITION_MATERIAL.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<AdditionalMaterial> additionalMaterials = (List<AdditionalMaterial>) deserialize;

        additionalMaterials.forEach(System.out::println);
        for (AdditionalMaterial additionalMaterial : additionalMaterials) {
            additionalMaterialRepo.save(additionalMaterial);
        }
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
    }

    public List<AdditionalMaterial> printAllWithGroupingByLectureId() {
        return additionalMaterialRepo.printAddMaterialsGroupingByLectureId();
    }

}
