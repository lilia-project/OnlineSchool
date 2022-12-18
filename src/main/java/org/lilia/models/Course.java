package org.lilia.models;

public class Course {
    private static int counter = 0;

    private final int id;

    public static void setCounter(int counter) {
        Course.counter = counter;
    }

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public Course() {
        counter++;
        id = counter;
    }
}
