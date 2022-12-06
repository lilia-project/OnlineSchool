package org.lilia.service;

import org.lilia.models.Course;

public class CourseService {
    public Course createCourse(int id){
        return new Course(id);
    }
    public Course createCourse(int id, int idTeacher, int idLecture, int idStudent){
        return new Course(id, idTeacher, idLecture, idStudent);
    }
}
