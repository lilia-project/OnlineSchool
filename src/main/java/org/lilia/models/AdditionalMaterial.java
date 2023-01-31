package org.lilia.models;

public class AdditionalMaterial {
    private static int counter = 0;

    private final int id;
    private final String name;
    private int lectureId;
    private ResourceType resourceType;


    public AdditionalMaterial(String name) {
        this.name = name;
        counter++;
        id = counter;
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
}
