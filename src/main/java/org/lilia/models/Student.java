package org.lilia.models;

public class Student {
    private static int counter;

    private final int id;

    public int getId() {
        return id;
    }

    public Student() {
        counter++;
        id = counter;
    }
}
