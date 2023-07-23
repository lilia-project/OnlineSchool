package org.lilia.dto;

import lombok.EqualsAndHashCode;
import org.lilia.entity.ResourceType;

@EqualsAndHashCode
public class AdditionalMaterialDto {
    private final int lectureId;
    private final String name;
    private final ResourceType resourceType;

    public AdditionalMaterialDto(int lectureId, String name, ResourceType resourceType) {
        this.lectureId = lectureId;
        this.name = name;
        this.resourceType = resourceType;
    }


    public int getLectureId() {
        return lectureId;
    }

    public String getName() {
        return name;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public String toString() {
        return "lectureId " + lectureId +
                "additionalMaterialName '" + name + "'" +
                "resourceType " + resourceType;
    }
}
