package org.lilia.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Component
@Data
public class Homework implements Serializable {

    private Integer id;

    private int lectureId;
    private String task;
    transient private LocalDate deadline;


    public Homework(Integer id, int lectureId, String task) {
        this.id = id;
        this.lectureId = lectureId;
        this.task = task;
    }

    public Homework() {
    }

    @Override
    public String toString() {
        return "\n homeworkId = " + id +
                "\n homeworkDeadline = " + deadline +
                "\n lecture's id = " + lectureId +
                "\n homework's name = '" + task + "'";
    }

}
