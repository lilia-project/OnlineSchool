package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.dto.CourseDto;
import org.lilia.exception.NoSuchCourseIdException;
import org.lilia.model.Course;
import org.lilia.model.Lecture;
import org.lilia.repository.CourseRepository;
import org.lilia.util.ConsoleUtils;

import java.util.List;
import java.util.Optional;

public class CourseService {

    private final CourseRepository courseRepository;
    private final LectureService lectureService;

    public CourseService(CourseRepository courseRepository, LectureService lectureService) {
        this.courseRepository = courseRepository;
        this.lectureService = lectureService;
    }

    public void sortByName() {
        courseRepository.sortByName();
    }

    public void createNewCourse(String courseName) {
        if (courseName == null) {
            throw new IllegalArgumentException("course name is null");
        }
        int courseId = courseRepository.insertValue(courseName);
        courseRepository.getById(courseId);
        ConsoleUtils.print(Constants.ELEMENT_CREATED);
    }

    public CourseDto createCourseDto(String courseName) {
        return new CourseDto(courseName);
    }

    public Course updateCourse(Course course, CourseDto courseDto) {
        if ((courseDto.getName()) != null) {
            course.setName(courseDto.getName());
        }
        return course;
    }

    public void outputAll() {
        courseRepository.getAllCourses();
    }

    public Course getRequireById(int courseId) {
        Optional<Course> course = courseRepository.getById(courseId);
        if (course.isEmpty()) {
            throw new NoSuchCourseIdException(courseId);
        }
        addLectureIntoCourse(course.get());
        return course.get();
    }

    private void addLectureIntoCourse(Course course) {
        List<Lecture> list = lectureService.findAllByCourseId(course.getId());
        course.setList(list);
    }

    public void deleteById(int courseId) {
        Course course = getRequireById(courseId);
        courseRepository.remove(course);
        ConsoleUtils.print(Constants.ELEMENT_DELETED);
    }

    public int courseIdIsValid() {
        int courseId = ConsoleUtils.readInteger();
        Optional<Course> course = courseRepository.getById(courseId);
        while (course.isEmpty()) {
            System.out.println("input valid course's id");
            courseId = ConsoleUtils.readInteger();
            course = courseRepository.getById(courseId);
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
