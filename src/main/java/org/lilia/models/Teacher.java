package org.lilia.models;

public class Teacher {
    private static int counter;

    private final int id;
    private int idCourse;

    public Teacher() {
        counter++;
        id = counter;
    }

    public Teacher(int idCourse) {
        this.idCourse = idCourse;
        counter++;
        id = counter;
    }
}
