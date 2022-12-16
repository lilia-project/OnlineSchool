package org.lilia.models;

public class AdditionalMaterial {
    private static int counter;

    private final int id;
    private int idLecture;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public AdditionalMaterial() {
        counter++;
        id = counter;
    }
}
