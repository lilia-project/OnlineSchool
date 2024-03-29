package org.lilia.entity;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Cacheable
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE, region = "course")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Value("${course.id}")
    private Integer id;

    @Value("${course.name}")
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Lecture> lectures = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private Set<Person> people = new HashSet<>();

    public Course() {
    }

    public Course(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return " course id = " + id +
                " course name = " + name +
                " lectures = " + lectures
                ;
    }

}

