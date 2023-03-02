package org.lilia.serialization;

public enum FilePath {
    FILE_PATH_COURSE(1, "src/main/resources/courseBackup.txt"),
    FILE_PATH_LECTURE(2, "src/main/resources/lectureBackup.txt"),
    FILE_PATH_TEACHER(3, "src/main/resources/teacherBackup.txt"),
    FILE_PATH_STUDENT(4, "src/main/resources/studentBackup.txt"),
    FILE_PATH_HOMEWORK(5, "src/main/resources/homeworkBackup.txt"),
    FILE_PATH_ADDITION_MATERIAL(6, "src/main/resources/addMaterialBackup.txt");

    private final int number;
    private final String path;

    public int getNumber() {
        return number;
    }

    public String getPath() {
        return path;
    }

    FilePath(int number, String path) {
        this.number = number;
        this.path = path;
    }
}
