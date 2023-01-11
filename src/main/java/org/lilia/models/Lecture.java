package org.lilia.models;

public class Lecture extends Model {
    public static int counter = 0;

    private final int courseId;
    private int personId;
    private String description;

    public Lecture(int courseId, String name, int personId, String description) {
        super(name, ++counter);
        this.courseId = courseId;
        this.personId = personId;
        this.description = description;
    }

    public Lecture(int courseId, String name) {
        super(name, ++counter);
        this.courseId = courseId;
    }

    public Lecture(int courseId, String name, String description) {
        super(name, ++counter);
        this.courseId = courseId;
        this.description = description;
    }

    public int getPersonId() {
        return personId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "\n courseId = " + courseId +
                "\n teacherId = " + personId +
                "\n lectureId = " + getId() +
                "\n lectureName = '" + getName() + "'," +
                "\n lectureDescription = '" + description + "'";
    }
}
