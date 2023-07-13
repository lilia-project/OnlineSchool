package org.lilia.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.List;

@Data
public class Course implements Serializable {
    @Value("${course.id}")
    private Integer id;
    @Value("${course.name}")
    private String name;

    private List<Lecture> list;

    @Autowired
    public Course(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

//    public Course() {
//    }

    @Override
    public String toString() {
        return " course id = " + id +
                " course name = " + name +
                " lectures = " + list;
    }

}

