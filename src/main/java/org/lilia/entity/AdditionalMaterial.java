package org.lilia.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;

@Entity
@Data
public class AdditionalMaterial implements Serializable {
    int counter = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int lectureId;

    @Enumerated
    private ResourceType resourceType;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public AdditionalMaterial() {
    }

    public AdditionalMaterial(String name, int lectureId) {
        this.name = name;
        this.lectureId = lectureId;
        counter++;
        id = counter;
    }

    @Override
    public String toString() {
        return "\n AdditionalMaterial{" +
                " id=" + id +
                " name='" + name + '\'' +
                " lectureId=" + lectureId +
                " resourceType=" + resourceType +
                '}';
    }

    public enum SortField {
        ID(1),
        LECTURE_ID(2),
        RESOURCE_TYPE(3);

        private final int id;

        SortField(int id) {
            this.id = id;
        }

        public static SortField getById(int id) {
            SortField[] values = values();
            for (SortField sortField : values) {
                if (sortField.id == id) {
                    return sortField;
                }
            }
            throw new NoSuchElementException("SortField by id " + id + " was not found");
        }
    }

    public static class IdComparator implements Comparator<AdditionalMaterial> {

        @Override
        public int compare(AdditionalMaterial o1, AdditionalMaterial o2) {
            return Integer.compare(o1.id, o2.id);
        }
    }

    public static class LectureIdComparator implements Comparator<AdditionalMaterial> {

        @Override
        public int compare(AdditionalMaterial o1, AdditionalMaterial o2) {
            return Integer.compare(o1.lectureId, o2.lectureId);
        }
    }

    public static class ResourceTypeComparator implements Comparator<AdditionalMaterial> {

        @Override
        public int compare(AdditionalMaterial o1, AdditionalMaterial o2) {

            return Integer.compare(o1.resourceType.getParameter(), o2.resourceType.getParameter());
        }
    }
}


