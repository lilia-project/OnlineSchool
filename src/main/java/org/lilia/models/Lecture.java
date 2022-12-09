package org.lilia.models;

public class Lecture {
    public static int id;
    public String nameLecture;
    public static int counter;
    public int idCourse;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;


    public Lecture(int idCourse, String nameLecture) {
        this.idCourse = idCourse;
        this.nameLecture = nameLecture;
        counter++;
        id = counter;
    }

    public String toString() {
        return "Lecture is created: id = " + id + ", course = " + idCourse + ", name - " + nameLecture + "\n___________";
    }
}
