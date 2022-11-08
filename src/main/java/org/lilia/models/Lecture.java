package org.lilia.models;

public class Lecture {
    private int id;
    public static int counter;
    public int idCourse;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;

    public Lecture(int id, int idCourse) {
        this.id = id;
        this.idCourse = idCourse;
        counter++;
    }
}
