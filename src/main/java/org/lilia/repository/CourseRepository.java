package org.lilia.repository;

import org.lilia.model.Course;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private static final List<Course> data = new ArrayList<>();


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

    public void sortByName() {
        data.sort(new Course.CourseIDComparator());
        System.out.println(data);
    }

    public void serializeCourses() {
        for (Course course : data) {
            Serializer.serialize(course, FilePath.FILE_PATH_COURSE);
        }
    }

    public void deserialize() {
        String filePath = FilePath.FILE_PATH_COURSE.getPath();
        System.out.println(Serializer.deserialize(filePath));
    }
}
