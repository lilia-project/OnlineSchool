package org.lilia.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Lecture implements Serializable {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MMM d, EEE HH:mm:ss", Locale.ENGLISH);
    private static int counter = 0;
    private final Integer id;
    private final LocalDateTime createdAt;
    private final LocalDateTime lectureDate;
    private String name;
    private int courseId;
    private int personId;
    private String description;
    private List<Homework> homeworkList;

    public Lecture(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.lectureDate = LocalDateTime.now().plusDays((int) (Math.random() * 10));
        counter++;
        id = counter;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return " Lecture{" +
                " id=" + id +
                " createdAt=" + createdAt.format(DTF) +
                " lectureDate=" + lectureDate.format(DTF) +
                " name='" + name + '\'' +
                " courseId=" + courseId +
                " personId=" + personId +
                " description='" + description + '\'' +
                " homeworkList=" + homeworkList +
                '}';
    }

    public LocalDateTime getLectureDate() {
        return lectureDate;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Homework> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<Homework> homeworkList) {
        this.homeworkList = homeworkList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return courseId == lecture.courseId && personId == lecture.personId && Objects.equals(id, lecture.id) && Objects.equals(createdAt, lecture.createdAt) && Objects.equals(lectureDate, lecture.lectureDate) && Objects.equals(name, lecture.name) && Objects.equals(description, lecture.description) && Objects.equals(homeworkList, lecture.homeworkList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, lectureDate, name, courseId, personId, description, homeworkList);
    }

}
