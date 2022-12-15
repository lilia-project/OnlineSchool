package org.lilia.models;

public class Course {
    private static int counter = 0;
    
    public final int id;
    private int idStudent;
    private int idTeacher;

    public Course() {
        counter++;
        id = counter;
    }
    public Course(int idTeacher, int idStudent){
        this.idTeacher = idTeacher;
        this.idStudent = idStudent;
        counter++;
        id = counter;
    }
}
