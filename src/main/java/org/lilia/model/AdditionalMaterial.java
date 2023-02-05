package org.lilia.model;

import java.util.Comparator;

public class AdditionalMaterial {
    private static int counter = 0;

    private final int id;

    private String name;

    private int lectureId;
    private ResourceType resourceType;

    public AdditionalMaterial(String name) {
        this.name = name;
        counter++;
        id = counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLectureId() {
        return lectureId;
    }

    @Override
    public String toString() {
        return "\n additionalMaterialId = " + id +
                "\n additionalMaterialName = '" + name + "'," +
                "\n lectureId = " + lectureId +
                "\n resourceType = " + resourceType;
    }

    public static class IdComparator implements Comparator<AdditionalMaterial> {

        @Override
        public int compare(AdditionalMaterial o1, AdditionalMaterial o2) {
            if (o1.id > o2.id) {
                return 1;
            } else if (o1.id < o2.id) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static class LectureIdComparator implements Comparator<AdditionalMaterial> {

        @Override
        public int compare(AdditionalMaterial o1, AdditionalMaterial o2) {
            if (o1.lectureId > o2.lectureId) {
                return 1;
            } else if (o1.lectureId < o2.lectureId) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static class ResourceTypeComparator implements Comparator<AdditionalMaterial> {

        @Override
        public int compare(AdditionalMaterial o1, AdditionalMaterial o2) {

            if (o1.resourceType.getParameter() > o2.resourceType.getParameter()) {
                return 1;
            } else if (o1.resourceType.getParameter() < o2.resourceType.getParameter()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public enum SortField {
        ID,
        LECTURE_ID,
        RESOURCE_TYPE
    }

}


