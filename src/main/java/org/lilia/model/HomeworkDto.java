package org.lilia.model;

public class HomeworkDto {
    private String task;

    private String additionalMaterial;

    public HomeworkDto(String name, String additionalMaterial) {
        this.task = name;
        this.additionalMaterial = additionalMaterial;
    }

    public HomeworkDto() {
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

    @Override
    public String toString() {
        return "\n lectureName = '" + task + "'," +
                "\n lectureDescription = '" + additionalMaterial + "'";
    }
}
