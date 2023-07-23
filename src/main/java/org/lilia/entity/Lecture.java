package org.lilia.entity;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Cacheable
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE, region = "lecture")
public class Lecture implements Serializable {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "lecture_date")
    private LocalDateTime lectureDate;
    private String name;
    private int courseId;
    private int personId;
    private String description;

    @ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;

    @OneToMany(mappedBy = "lecture")
    private List<Homework> homeworkList = new ArrayList<>();

    public Lecture() {
    }

    public Lecture(final Integer id, String name) {
        this.id = id;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.lectureDate = LocalDateTime.now().plusDays((int) (Math.random() * 10));
    }

    public Lecture(Integer id, LocalDateTime createdAt, LocalDateTime lectureDate, String name, int courseId, int personId, String description) {
        this.id = id;
        this.createdAt = createdAt;
        this.lectureDate = lectureDate;
        this.name = name;
        this.courseId = courseId;
        this.personId = personId;
        this.description = description;
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
