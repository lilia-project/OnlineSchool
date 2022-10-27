package org.lilia.models;

public class Course {
    private int id;
    public static int counter;
    private String name;
    private Lecture lecture;
    private Student student;
    private Teacher teacher;

    public Course() {
        counter++;
    }
}
