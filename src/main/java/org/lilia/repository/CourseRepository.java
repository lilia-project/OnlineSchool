package org.lilia.repository;

import org.lilia.model.Course;

import java.util.*;

public class CourseRepository{
   private static final SortedSet<Course> data = new TreeSet<>();


    public void add(Course course) {
        data.add(course);
    }

    public void remove(Course course) {
        data.remove(course);
    }

    public Optional<Course> getById(int courseId) {
        for (Course course : data) {
            if (course.getId() == courseId) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    public void getAll() {
        for (Course course : data) {
            System.out.println(course);
        }
    }



}
