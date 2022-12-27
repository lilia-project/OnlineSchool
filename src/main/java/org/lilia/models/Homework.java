package org.lilia.models;

public class Homework {
    private static int counter;

    private final int id;
    private AdditionalMaterial additionalMaterial;

    public Homework() {
        counter++;
        id = counter;
    }
}
