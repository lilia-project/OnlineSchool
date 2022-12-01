package org.lilia.models;

public class Lecture {
    public int id;
    public String nameLecture;
    public static int counter;
    public int idCourse;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;

    public Lecture(int id, int idCourse, String nameLecture) {
        this.id = id;
        this.idCourse = idCourse;
        this.nameLecture = nameLecture;
        counter++;
    }
}
