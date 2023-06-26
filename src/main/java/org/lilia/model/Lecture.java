package org.lilia.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
@Data
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

}
