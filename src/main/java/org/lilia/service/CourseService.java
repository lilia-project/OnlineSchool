package org.lilia.service;

import org.lilia.models.Course;

public class CourseService {
    public Course createCourse(){
        return new Course();
    }
    public Course createCourse(int idTeacher, int idLecture, int idStudent){
        return new Course(idTeacher, idLecture, idStudent);
    }
}
