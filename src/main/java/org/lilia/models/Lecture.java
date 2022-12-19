package org.lilia.models;

public class Lecture extends Model {
    public static int counter = 0;

    private final int idCourse;

    public int getIdCourse() {
        return idCourse;
    }

    public Lecture(int idCourse, String name) {
        super(name, ++counter);
        this.idCourse = idCourse;
    }
    @Override
    public String toString() {
        return "lecture: " +
                "id = " + getId() +
                ", nameLecture = '" + getName() + "'," +
                " idCourse = " + idCourse;
    }
}
