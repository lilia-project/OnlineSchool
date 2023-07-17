package org.lilia.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Homework implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int lectureId;
    private String task;
    transient private LocalDate deadline;

    public Homework() {
    }

    public Homework(Integer id, int lectureId, String task) {
        this.id = id;
        this.lectureId = lectureId;
        this.task = task;
    }

    @Override
    public String toString() {
        return "\n homeworkId = " + id +
                "\n homeworkDeadline = " + deadline +
                "\n lecture's id = " + lectureId +
                "\n homework's name = '" + task + "'";
    }

}
