package org.lilia.models;

public class Course {
    private static int id;
    public static int counter;

    private String name;
    private Lecture lecture;
    private Student student;
    private Teacher teacher;

    public Course(int id) {
        this.id = id;
        counter++;
    }
}
