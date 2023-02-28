package org.lilia.model;

import java.util.List;

public class Lecture {
    public static int counter = 0;

    private final Integer id;
    private String name;
    private int courseId;
    private int personId;
    private String description;

    private List<Homework> list;

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
                "\n teacherId = " + personId +
                "\n homeworks = " + list + "\n";
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

    public void setList(List<Homework> list) {
        this.list = list;
    }
}
