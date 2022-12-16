package org.lilia.models;

public class Course {
    private static int counter = 0;

    private final int id;
    private int idStudent;
    private int idTeacher;

    public int getId() {
        return id;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public Course() {
        counter++;
        id = counter;
    }

    public Course(int idTeacher, int idStudent) {
        this.idTeacher = idTeacher;
        this.idStudent = idStudent;
        counter++;
        id = counter;
    }
}
