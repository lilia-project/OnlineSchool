package org.lilia.service;

import org.lilia.models.Course;

public class CourseService {
    public Course createCourse(String name) {
        return new Course(name);
    }
}
