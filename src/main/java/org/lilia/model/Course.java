package org.lilia.model;

import java.util.Comparator;
import java.util.List;

public class Course implements Comparable<Course> {
    private static int counter = 0;

    private final Integer id;
    private String name;

    private List<Lecture> list;

    public Course(String name) {
        this.name = name;
        counter++;
        id = counter;
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

    public void setList(List<Lecture> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "\n course id = " + id +
                "\n course name = " + name +
                "\n lectures = " + list;
    }

    @Override
    public int compareTo(Course course) {
        return this.name.compareTo(course.name);
    }

}
