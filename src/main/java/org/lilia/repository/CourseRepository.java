package org.lilia.repository;

import org.lilia.model.Course;

public interface CourseRepository {

    void add(Course element);

    void add(int index, Course element);

    Course remove(int index);

    Course getE(int index);

    boolean isEmpty();

    int size();

    void expandingArray();

    Course[] getAll();
}
