package org.lilia.models;

public class Lecture extends Model {
    public static int counter = 0;

    private int courseId;
    private int personId;

    public Lecture() {
        super();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    private String description;

    public Lecture(String name) {
        super(name, ++counter);
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
        return "\n lectureId = " + getId() +
                "\n courseId = " + courseId +
                "\n teacherId = " + personId +
                "\n lectureName = '" + getName() + "'," +
                "\n lectureDescription = '" + description + "'\n";
    }
}
