package org.lilia.models;

public class Course {
    private int id;
    public static int counter;

    private Lecture lecture;
    private Student student;
    private Teacher teacher;

    public Course(int id) {
        this.id = id;
        counter++;
    }
}
