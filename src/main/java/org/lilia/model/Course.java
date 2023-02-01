package org.lilia.model;

public class Course {
    private static int counter = 0;

    private final Integer id;
    private String name;

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
}
