package org.lilia.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Value("${course.id}")
    private Integer id;
    @Value("${course.name}")
    private String name;
    @Transient
    private List<Lecture> list;

    @Autowired
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

