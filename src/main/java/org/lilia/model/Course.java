package org.lilia.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Course implements Serializable {

    private final Integer id;
    private String name;

    private List<Lecture> list;

    public Course(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return " course id = " + id +
                " course name = " + name +
                " lectures = " + list;
    }

}

