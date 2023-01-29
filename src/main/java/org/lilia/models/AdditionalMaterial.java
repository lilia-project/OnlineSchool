package org.lilia.models;

public class AdditionalMaterial {
    private static int counter = 0;

    private final int id;
    private final String name;
    private int idLecture;
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

    public int getIdLecture() {
        return idLecture;
    }
}
