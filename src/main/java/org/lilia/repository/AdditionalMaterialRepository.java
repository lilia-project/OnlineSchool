package org.lilia.repository;

import org.lilia.models.AdditionalMaterial;

import java.util.ArrayList;
import java.util.List;

public class AdditionalMaterialRepository {
    List<AdditionalMaterial> list;

    public AdditionalMaterialRepository() {
        list = new ArrayList<>();
    }

    public List<AdditionalMaterial> getList() {
        return list;
    }

    public void setList(List<AdditionalMaterial> list) {
        this.list = list;
    }
}
