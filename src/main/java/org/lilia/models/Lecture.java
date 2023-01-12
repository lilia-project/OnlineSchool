package org.lilia.models;

public class Lecture extends Model {
    public static int counter = 0;

    private int courseId;
    private int personId;
    private String description;

    public Lecture(String name) {
        super(name, ++counter);
    }

    public Lecture(String name, String description) {
        super(name, ++counter);
        this.description = description;
    }

    public Lecture(int courseId, String name, String description) {
        super(name, ++counter);
        this.courseId = courseId;
        this.description = description;
    }

    public Lecture(int courseId, String name, String description, int personId) {
        super(name, ++counter);
        this.courseId = courseId;
        this.description = description;
        this.personId = personId;
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
