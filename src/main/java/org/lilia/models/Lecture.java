package org.lilia.models;

public class Lecture {
    public static int counter = 0;

    private final int id;
    private String name;
    private int courseId;
    private int personId;
    private String description;
    private String homework;
    private String[] homeworksList = new String[5];


    public Lecture(int courseId, String name, String description, String homework, int personId) {
        counter++;
        id = counter;
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.homework = homework;
        this.personId = personId;
    }

    private void add(String homework) {
        for (int i = 0; i <= 4; i++) {
            homeworksList[i] = homework;
        }
    }

    @Override
    public String toString() {
        return "\n lectureId = " + id +
                "\n courseId = " + courseId +
                "\n lectureName = '" + name + "'," +
                "\n lectureDescription = '" + description + "'" +
                "\n lectureHomework = '" + homework + "'" +
                "\n teacherId = " + personId;

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

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }
}
