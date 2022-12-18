package org.lilia.models;

public class Student {
    private static int counter;

    private final int id;
    private int idCourse;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Student.counter = counter;
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

    public Student(int idCourse) {
        this.idCourse = idCourse;
        counter++;
        id = counter;
    }
}
