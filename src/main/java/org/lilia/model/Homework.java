package org.lilia.model;

public class Homework {
    private static int counter;

    private final Integer id;

    private int lectureId;
    private String task;
    private String additionalMaterial;

    public Homework(int lectureId, String task, String additionalMaterial) {
        this.lectureId = lectureId;
        this.task = task;
        this.additionalMaterial = additionalMaterial;
        counter++;
        id = counter;
    }

    public int getId() {
        return id;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
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

    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "\n homeworkId = " + id +
                "\n lecture's id = " + lectureId +
                "\n homework's name = '" + task + "'" +
                "\n homework's additionalMaterial = '" + additionalMaterial + "'\n";
    }
}
