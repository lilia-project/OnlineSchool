package org.lilia.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Lecture implements Serializable {
    private static int counter = 0;

    private final Integer id;

    private final LocalDate createdAt;

    private final LocalDate lectureDate;

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

    public static int getCounter() {
        return counter;
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

    public LocalDate getCreatedAt() {
        return createdAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return courseId == lecture.courseId && personId == lecture.personId && Objects.equals(id, lecture.id) && Objects.equals(createdAt, lecture.createdAt) && Objects.equals(lectureDate, lecture.lectureDate) && Objects.equals(name, lecture.name) && Objects.equals(description, lecture.description) && Objects.equals(list, lecture.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, lectureDate, name, courseId, personId, description, list);
    }

    public static class LectureCreateAtComparator implements Comparator<Lecture>{

        @Override
        public int compare(Lecture o1, Lecture o2) {
            return (o1.getCreatedAt().compareTo(o2.getCreatedAt()));
        }
    }
}
