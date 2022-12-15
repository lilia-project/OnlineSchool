package org.lilia.service;

import org.lilia.models.Course;

public class CourseService {
    public Course createCourse() {
        return new Course();
    }

    public Course createCourse(int idTeacher, int idStudent) {
        return new Course(idTeacher, idStudent);
    }
}
