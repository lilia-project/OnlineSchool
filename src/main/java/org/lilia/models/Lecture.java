package org.lilia.models;

public class Lecture {
    public static int counter = 0;

    private final int id;

    private final int idCourse;
    private String nameLecture;

    public static int getCounter() {
        return counter;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public String getNameLecture() {
        return nameLecture;
    }

    public int getId() {
        return id;
    }

    public static void setCounter(int counter) {
        Lecture.counter = counter;
    }

    public void setNameLecture(String nameLecture) {
        this.nameLecture = nameLecture;
    }

    public Lecture(int idCourse) {
        this.idCourse = idCourse;
        counter++;
        id = counter;
    }

    public Lecture(int idCourse, String nameLecture) {
        this.idCourse = idCourse;
        this.nameLecture = nameLecture;
        counter++;
        id = counter;
    }

    @Override
    public String toString() {
        return "lecture: " +
                "id = " + id +
                ", nameLecture = '" + nameLecture + "'," +
                " idCourse = " + idCourse;
    }
}
