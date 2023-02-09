package org.lilia.repository;

import org.lilia.model.AdditionalMaterial;

import java.util.*;

public class AdditionalMaterialRepository {

    private final Map<Integer, List<AdditionalMaterial>> data = new HashMap<>();

    public void add(AdditionalMaterial additionalMaterial) {
        List<AdditionalMaterial> value = data.get(additionalMaterial.getLectureId());
        if (value == null) {
            data.put(additionalMaterial.getLectureId(), List.of(additionalMaterial));
        } else {
            value.add(additionalMaterial);
        }
    }

    public int size() {
        return data.size();
    }

    public Optional<AdditionalMaterial> getById(int id) {
        Collection<List<AdditionalMaterial>> values = data.values();
        for (List<AdditionalMaterial> additionalMaterials : values) {
            for (AdditionalMaterial additionalMaterial : additionalMaterials) {
                if (additionalMaterial.getId() == id) {
                    return Optional.of(additionalMaterial);
                }
            }
        }
        return Optional.empty();
    }

    public void remove(AdditionalMaterial additionalMaterial) {
        List<AdditionalMaterial> value = data.get(additionalMaterial.getLectureId());
        if (value == null) {
            return;
        }
        value.remove(additionalMaterial);
    }

    public void getAll() {
        for (List<AdditionalMaterial> additionalMaterials : data.values()) {

            for (AdditionalMaterial additionalMaterial : additionalMaterials) {

                System.out.println(additionalMaterial);
            }
        }
    }

    public Optional<List<AdditionalMaterial>> getByLectureId(int lectureId) {
        List<AdditionalMaterial> list = data.get(lectureId);
        return Optional.ofNullable(list);
    }

 /*   public void getAll(AdditionalMaterial.SortField sortField) {
        Comparator<AdditionalMaterial> comparator = null;
        switch (sortField) {
            case ID -> comparator = new AdditionalMaterial.IdComparator();
            case LECTURE_ID -> comparator = new AdditionalMaterial.LectureIdComparator();
            case RESOURCE_TYPE -> comparator = new AdditionalMaterial.ResourceTypeComparator();
            default -> ConsoleUtils.print(Constants.ERROR);
        }
        list.sort(comparator);
        for (AdditionalMaterial additionalMaterial : list) {
            System.out.println(additionalMaterial);
        }
    }*/
}
