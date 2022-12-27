package org.lilia.models;

public class Teacher {
    private static int counter;

    private final int id;

    public Teacher() {
        counter++;
        id = counter;
    }
}
