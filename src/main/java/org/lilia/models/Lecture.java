package org.lilia.models;
public class Lecture extends Model {
    public static int counter = 0;

    private final int courseId;
    private final int personId;

    public Lecture(int courseId, String name, int personId) {
        super(name, ++counter);
        this.courseId = courseId;
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "\n idCourse = " + getCourseId() +
                "\n idTeacher = " + personId +
                "\n idLecture = " + getId() +
                "\n nameLecture = '" + getName() + "',";
    }
}
