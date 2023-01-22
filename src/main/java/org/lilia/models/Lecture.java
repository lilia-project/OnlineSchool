package org.lilia.models;

import java.util.Arrays;

public class Lecture {
    public static int counter = 0;

    private final int id;
    private String name;
    private int courseId;
    private int personId;
    private String description;

    private Homework[] homeworksList;

    public Lecture(String name) {
        this.name = name;
        counter++;
        id = counter;
    }

    @Override
    public String toString() {
        return "\n lectureId = " + id +
                "\n courseId = " + courseId +
                "\n lectureName = '" + name + "'," +
                "\n lectureDescription = '" + description + "'" +
                "\n teacherId = " + personId + "\n" +
                "\n lecture's homework = \n" + Arrays.toString(homeworksList);
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

    public void setHomeworksList(Homework[] homeworksList) {
        this.homeworksList = homeworksList;
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

    public Homework[] getHomeworksList() {
        return homeworksList;
    }
}
