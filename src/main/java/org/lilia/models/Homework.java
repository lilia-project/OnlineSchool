package org.lilia.models;

public class Homework {
    private static int counter;

    private final int id;
    private int idLecture;


    public int getId() {
        return id;
    }

    public Homework() {
        counter++;
        id = counter;
    }
}
