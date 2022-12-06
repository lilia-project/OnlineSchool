package org.lilia.models;

public class Course {
    public int id;
    public static int counter;

    public int idLecture;
    public int idStudent;
    public int idTeacher;

    public Course(int id) {
        this.id = id;
        //this.idLecture = idLecture;
        counter++;
    }
    public Course(int id, int idTeacher, int idLecture, int idStudent){
        this.id = id;
        this.idTeacher = idTeacher;
        this.idLecture = idLecture;
        this.idStudent = idStudent;
    }
}
