package org.lilia.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Data
public class Course implements Serializable {

    private Integer id;
    private String name;

    private List<Lecture> list;

    public Course(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return " course id = " + id +
                " course name = " + name +
                " lectures = " + list;
    }

}

