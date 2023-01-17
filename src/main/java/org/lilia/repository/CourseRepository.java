package org.lilia.repository;

import org.lilia.models.Course;
import org.lilia.models.Lecture;

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
