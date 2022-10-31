package org.lilia.models;

public class Lecture {
    private int id;
    public static int counter;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;

    public Lecture(int id) {
        this.id = id;
        counter++;
    }
}
