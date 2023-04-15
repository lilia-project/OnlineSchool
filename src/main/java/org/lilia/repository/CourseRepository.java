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
    private static final List<Course> list = new ArrayList<>();

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

    public void getAll() {
        for (Course course : list) {
            System.out.println(course);
        }
    }

    public void sortByName() {
        list.sort(new Course.CourseIDComparator());
        System.out.println(list);
    }

    public void serializeCourses() {
        Serializer.serialize(list, FilePath.FILE_PATH_COURSE);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserialize() {
        String filePath = FilePath.FILE_PATH_COURSE.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<Course> courses = (List<Course>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);

        for (Course course : courses) {
            saveCourse(course);
        }
    }

    private void saveCourse(Course course) {
        for (Course currentCourse : list) {
            if (currentCourse.getId() == course.getId()) {
                ConsoleUtils.print(course.getId() + " - this course id already exists");
            }
        }
        list.add(course);
    }
}
