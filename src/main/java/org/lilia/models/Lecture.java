package org.lilia.models;

public class Lecture {
    public static int counter = 0;

    public int id;
    public String nameLecture;
    public int idCourse;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;

    @Override
    public String toString() {
        return "lecture: " +
                "id = " + id +
                ", nameLecture = '" + nameLecture + "'," +
                " idCourse = " + idCourse;
    }

    public Lecture(int idCourse, String nameLecture) {
        this.idCourse = idCourse;
        this.nameLecture = nameLecture;
        counter++;
        id = counter;
    }
}
