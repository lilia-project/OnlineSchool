package org.lilia.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Lecture implements Serializable {
    public static int counter = 0;

    private final Integer id;
    private final LocalDateTime createdAt;
    private LocalDateTime lectureDate;
    private String name;
    private int courseId;
    private int personId;
    private String description;
    transient DateTimeFormatter dTF = DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm:ss:SSS", Locale.ENGLISH);
    transient DateTimeFormatter dTF1 = DateTimeFormatter.ofPattern("MMM d,eeee HH:mm:ss", Locale.ENGLISH);

    private transient List<Homework> list;

    public Lecture(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.lectureDate = LocalDateTime.now().plusDays(2);
        counter++;
        id = counter;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", createdAt=" + createdAt.format(dTF) +
                ", lectureDate=" + lectureDate.format(dTF1) +
                ", name='" + name + '\'' +
                ", courseId=" + courseId +
                ", personId=" + personId +
                ", description='" + description + '\'' +
                ", list=" + list +
                '}';
    }

    public LocalDateTime getLocalDateTime() {
        return createdAt;
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
