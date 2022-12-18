package org.lilia.models;

public class AdditionalMaterial {
    private static int counter;

    private final int id;
    private int idLecture;
    private String name;

    public static int getCounter() {
        return counter;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getIdLecture() {
        return idLecture;
    }

    public static void setCounter(int counter) {
        AdditionalMaterial.counter = counter;
    }

    public void setIdLecture(int idLecture) {
        this.idLecture = idLecture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdditionalMaterial() {
        counter++;
        id = counter;
    }
}
