package org.lilia.repository;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.model.Course;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private static final List<Course> courseList = new ArrayList<>();

    public void add(Course course) {
        courseList.add(course);
    }

    public void remove(Course course) {
        courseList.remove(course);
    }

    public Optional<Course> getById(int courseId) {
        return courseList.stream()
                .filter(i -> i.getId() == courseId)
                .findFirst();

        /*for (Course course : courseList) {
            if (course.getId() == courseId) {
                return Optional.of(course);
            }
        }
        return Optional.empty();*/
    }

    public void getAll() {
        for (Course course : courseList) {
            System.out.println(course);
        }
    }

    public void sortByName() {
        courseList.sort(new Course.CourseIDComparator());
        System.out.println(courseList);
    }

    public void serializeCourses() {
        Serializer.serialize(courseList, FilePath.FILE_PATH_COURSE);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserializeCourses() {
        String filePath = FilePath.FILE_PATH_COURSE.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<Course> courses = (List<Course>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
        courseList.addAll(courses);
    }
}


