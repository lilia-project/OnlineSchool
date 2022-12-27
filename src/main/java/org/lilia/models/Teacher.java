package org.lilia.models;

public class Teacher extends Model {
    private static int counter = 0;

    private int idCourse;

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public Teacher(String name, int idCourse) {
        super(name, ++counter);
        this.idCourse = idCourse;
    }
}
