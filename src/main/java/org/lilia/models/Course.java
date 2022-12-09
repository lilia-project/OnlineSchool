package org.lilia.models;

public class Course {
    public static int counter = 0;

    public int id;
    public int idLecture;
    public int idStudent;
    public int idTeacher;

    public Course() {
        counter++;
        id = counter;
    }
    public Course(int idTeacher, int idLecture, int idStudent){
        this.idTeacher = idTeacher;
        this.idLecture = idLecture;
        this.idStudent = idStudent;
        counter++;
        id = counter;
    }
}
