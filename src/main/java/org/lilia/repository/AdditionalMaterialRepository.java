package org.lilia.repository;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.model.AdditionalMaterial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AdditionalMaterialRepository {
    private final List<AdditionalMaterial> list = new ArrayList<>();

    public void add(AdditionalMaterial additionalMaterial) {
        list.add(additionalMaterial);
    }

    public int size() {
        return list.size();
    }

    public Optional<AdditionalMaterial> getById(int additionalMaterialId) {
        for (AdditionalMaterial additionalMaterial : list) {
            if (additionalMaterial.getId() == additionalMaterialId) {
                return Optional.of(additionalMaterial);
            }
        }
        return Optional.empty();
    }

    public void remove(AdditionalMaterial additionalMaterial) {
        list.remove(additionalMaterial);
    }

    public void getAll() {
        for (AdditionalMaterial additionalMaterial : list) {
            System.out.println(additionalMaterial);
        }
    }

    public void getAll(AdditionalMaterial.SortField sortField) {
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
    }
}
