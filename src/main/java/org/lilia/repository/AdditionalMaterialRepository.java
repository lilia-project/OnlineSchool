package org.lilia.repository;

import org.lilia.models.AdditionalMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdditionalMaterialRepository {
    private final List<AdditionalMaterial> list = new ArrayList<>();

    public List<AdditionalMaterial> getList() {
        return list;
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
}
