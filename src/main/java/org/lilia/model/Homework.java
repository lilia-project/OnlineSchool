package org.lilia.model;

import java.util.Objects;

public class Homework {
    private static int counter;

    private final Integer id;

    private int lectureId;
    private String task;

    public Homework(int lectureId, String task) {
        this.lectureId = lectureId;
        this.task = task;
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

    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "\n homeworkId = " + id +
                "\n lecture's id = " + lectureId +
                "\n homework's name = '" + task + "'";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Homework homework = (Homework) o;

        if (lectureId != homework.lectureId) return false;
        if (!Objects.equals(id, homework.id)) return false;
        return Objects.equals(task, homework.task);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + lectureId;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
    }
}
