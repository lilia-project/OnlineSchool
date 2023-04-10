package org.lilia.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Lecture implements Serializable {
    public static int counter = 0;

    private final Integer id;
    private final LocalDate createdAt;

    private LocalDate lectureDate;

    private String name;
    private int courseId;
    private int personId;
    private String description;
    private transient List<Homework> list;

    public Lecture(String name) {
        this.name = name;
        this.createdAt = LocalDate.now();
        this.lectureDate = LocalDate.now().plusDays((int) (Math.random() * 10));
        counter++;
        id = counter;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", lectureDate=" + lectureDate +
                ", name='" + name + '\'' +
                ", courseId=" + courseId +
                ", personId=" + personId +
                ", description='" + description + '\'' +
                ", list=" + list +
                '}';
    }

    public LocalDate getLectureDate() {
        return lectureDate;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<Homework> list) {
        this.list = list;
    }
}
