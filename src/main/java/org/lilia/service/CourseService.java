package org.lilia.service;

import org.lilia.model.Course;

public class CourseService {
    public Course createCourse(String name) {
        return new Course(name);
    }
}
