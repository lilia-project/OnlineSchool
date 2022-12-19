package org.lilia.models;

public class Homework extends Model {
    private static int counter;

    private int idLecture;

    public int getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(int idLecture) {
        this.idLecture = idLecture;
    }

    public Homework(String name, int idLecture) {
        super(name, ++counter);
    }
}
