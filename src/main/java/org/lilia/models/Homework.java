package org.lilia.models;

public class Homework {
    private static int counter;

    private final int id;
    private int idLecture;
    private String task;
    private String additionalMaterial;


    public Homework(int idLecture, String task, String additionalMaterial) {
        this.idLecture = idLecture;
        this.task = task;
        this.additionalMaterial = additionalMaterial;
        counter++;
        id = counter;
    }

    public int getId() {
        return id;
    }

    public int getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(int idLecture) {
        this.idLecture = idLecture;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getAdditionalMaterial() {
        return additionalMaterial;
    }

    public void setAdditionalMaterial(String additionalMaterial) {
        this.additionalMaterial = additionalMaterial;
    }
}
