package org.lilia.models;

public class Teacher {
    private static int counter;

    private final int id;
    private int idCourse;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Teacher.counter = counter;
    }

    public int getId() {
        return id;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

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
