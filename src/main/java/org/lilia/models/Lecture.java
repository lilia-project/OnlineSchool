package org.lilia.models;

public class Lecture {
    private int id;
    public static int counter;
    public int courseId;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;

    public Lecture(int id,int courseId) {
        this.id = id;
        this.courseId = courseId;
        counter++;
    }
}
