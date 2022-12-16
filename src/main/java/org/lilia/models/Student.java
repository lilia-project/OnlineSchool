package org.lilia.models;

public class Student {
    private static int counter;

    private final int id;
    private int idCourse;

    public Student(int idCourse) {
        this.idCourse = idCourse;
        counter++;
        id = counter;
    }

    public Student() {
        counter++;
        id = counter;
    }

    public int getId() {
        return id;
    }
}
