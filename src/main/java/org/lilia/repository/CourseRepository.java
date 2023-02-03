package org.lilia.repository;

import org.lilia.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private final List<Course> list = new ArrayList<>();

    public void add(Course course) {
        list.add(course);
    }

    public void remove(Course course) {
        list.remove(course);
    }

    public Optional<Course> getById(int courseId) {
        for (Course course : list) {
            if (course.getId() == courseId) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    public int size() {
        return list.size();
    }

    public void getAll() {
        for (Course course : list) {
            System.out.println(course);
        }
    }

}
