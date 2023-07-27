package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.entity.Course;
import org.lilia.entity.Lecture;
import org.lilia.exception.NoSuchCourseIdException;
import org.lilia.repository.CourseRepo;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepo courseRepo;
    private final LectureService lectureService;

    @Autowired
    public CourseService(CourseRepo courseRepo, LectureService lectureService) {
        this.courseRepo = courseRepo;
        this.lectureService = lectureService;
    }

    private void addLectureIntoCourse(Course course) {
        List<Lecture> list = lectureService.findAllByCourseId(course.getId());
        course.setLectures(list);
    }

    public List<Course> sortByName() {
        return courseRepo.sortByName();
    }

    public void createNewCourse(String courseName) {
        if (courseName == null) {
            throw new IllegalArgumentException("course name is null");
        }
        Course course = new Course();
        course.setName(courseName);
        courseRepo.save(course);
    }

    public void updateCourse(Course course, String name) {
        course.setName(name);
        courseRepo.updateCourse(course);
    }


    public List<Course> outputAll() {
        return courseRepo.findAll();
    }

    public Course getRequireById(int courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (course.isEmpty()) {
            throw new NoSuchCourseIdException(courseId);
        }
        addLectureIntoCourse(course.get());
        return course.get();
    }

    public void deleteById(int courseId) {
        courseRepo.deleteById(courseId);
    }

    public int courseIdIsValid() {
        int courseId = ConsoleUtils.readInteger();
        Optional<Course> course = courseRepo.findById(courseId);
        while (course.isEmpty()) {
            System.out.println("input valid course's id");
            courseId = ConsoleUtils.readInteger();
            course = courseRepo.findById(courseId);
        }
        return courseId;
    }

    public void backupCourses() {
        List<Course> courses = courseRepo.findAll();
        Serializer.serialize(courses, FilePath.FILE_PATH_COURSE);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }


    public void deserializeCourse() {
        String filePath = FilePath.FILE_PATH_COURSE.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<Course> courses = (List<Course>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
        courses.forEach(System.out::println);
        for (Course course : courses) {
            courseRepo.save(course);
        }
    }
}
