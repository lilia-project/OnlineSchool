package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.entity.Course;
import org.lilia.entity.Lecture;
import org.lilia.exception.NoSuchCourseIdException;
import org.lilia.repository.CourseRepository;
import org.lilia.util.ConsoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CourseService {

    private final CourseRepository courseRepository;
    private final LectureService lectureService;

    @Autowired
    public CourseService(CourseRepository courseRepository, LectureService lectureService) {
        this.courseRepository = courseRepository;
        this.lectureService = lectureService;
    }

    private static void addLectureIntoCourse(Course course) {
        List<Lecture> list = LectureService.findAllByCourseId(course.getId());
        course.setLectures(list);
    }

    public void sortByName() {
        courseRepository.sortByName();
    }

    public void createNewCourse(String courseName) {
        if (courseName == null) {
            throw new IllegalArgumentException("course name is null");
        }
        Course course = new Course();
        course.setName(courseName);
        courseRepository.save(course);
        ConsoleUtils.print(Constants.ELEMENT_CREATED);
    }

    public void updateCourse(int id, String name) {
        Course course = new Course();
        course.setName(name);
        courseRepository.update(course);
    }

    public List<Course> outputAll() {
        return courseRepository.getAllCourses().get();
    }

    public Course getRequireById(int courseId) {
        Optional<Course> course = courseRepository.get(courseId);
        if (course.isEmpty()) {
            throw new NoSuchCourseIdException(courseId);
        }
        addLectureIntoCourse(course.get());
        return course.get();
    }

    public void deleteById(int courseId) {
        Course course = getRequireById(courseId);
        courseRepository.delete(course);
        ConsoleUtils.print(Constants.ELEMENT_DELETED);
    }

    public int courseIdIsValid() {
        int courseId = ConsoleUtils.readInteger();
        Optional<Course> course = courseRepository.get(courseId);
        while (course.isEmpty()) {
            System.out.println("input valid course's id");
            courseId = ConsoleUtils.readInteger();
            course = courseRepository.get(courseId);
        }
        return courseId;
    }

    public void print(Course course) {
        System.out.println(course);
    }

    public void backupCourses() {
        courseRepository.serializeCourses();
    }

    public void deserialize() {
        courseRepository.deserializeCourses();
    }
}
