package org.lilia.models;

public class Homework {
    private static int counter;

    private final int id;
    private int idLecture;

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public int getIdLecture() {
        return idLecture;
    }

    public static void setCounter(int counter) {
        Homework.counter = counter;
    }

    public void setIdLecture(int idLecture) {
        this.idLecture = idLecture;
    }


    public Homework() {
        counter++;
        id = counter;
    }
}
