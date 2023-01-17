package org.lilia.models;

public class LectureDto {
    private String name;

    private String description;
    private String homework;
    private int teacherId;
    private int courseId;

    public LectureDto(int courseId, String name, String description, String homework, int teacherId) {
        this.name = name;
        this.description = description;
        this.homework = homework;
        this.teacherId = teacherId;
        this.courseId = courseId;
    }

    public LectureDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "\n courseId = " + courseId +
                "\n lectureName = '" + name + "'," +
                "\n lectureDescription = '" + description + "'" +
                "\n lectureHomework = '" + homework + "'" +
                "\n teacherId = " + teacherId;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }
}
