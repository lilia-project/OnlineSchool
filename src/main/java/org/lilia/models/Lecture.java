package org.lilia.models;

public class Lecture extends Model {
    public static int counter = 0;

    private final int idCourse;

    private int personId;

    public Lecture(int idCourse, String name, int personId) {
        super(name, ++counter);
        this.idCourse = idCourse;
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getIdCourse() {
        return idCourse;
    }

    @Override
    public String toString() {
        return "\n idCourse = " + getIdCourse() +
                "\n idLecture = " + getId() +
                "\n nameLecture = '" + getName() + "'," +
                "\n idTeacher = " + personId;
    }
}
