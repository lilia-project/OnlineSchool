package org.lilia.models;

public class Homework {
    private static int counter;

    private final int id;
    private AdditionalMaterial additionalMaterial;


    public int getId() {
        return id;
    }

    public AdditionalMaterial getAdditionalMaterial() {
        return additionalMaterial;
    }

    public void setAdditionalMaterial(AdditionalMaterial additionalMaterial) {
        this.additionalMaterial = additionalMaterial;
    }

    public Homework() {
        counter++;
        id = counter;
    }
}
