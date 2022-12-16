package org.lilia.models;

public class Course {
    private static int counter = 0;

    private final int id;

    public int getId() {
        return id;
    }

    public Course() {
        counter++;
        id = counter;
    }
}
