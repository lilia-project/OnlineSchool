package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.models.Teacher;
import org.lilia.service.CourseService;
import org.lilia.service.LectureService;
import org.lilia.service.TeacherService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CourseService courseService = new CourseService();

        Course course1 = courseService.createCourse(1);
        //Course course2 = courseService.createCourse(2);

        LectureService lectureService = new LectureService();

        Lecture lecture1 = lectureService.createLecture(1, course1.id);
        Lecture lecture2 = lectureService.createLecture(2, course1.id);
        Lecture lecture3 = lectureService.createLecture(3, course1.id);
        Lecture lecture4 = lectureService.createLecture(4, course1.id);
        Lecture lecture5 = lectureService.createLecture(5, course1.id);
        Lecture lecture6 = lectureService.createLecture(6, course1.id);

       /* Lecture lecture1 = lectureService.createLecture(1, course2.id);
        Lecture lecture2 = lectureService.createLecture(2, course2.id);
        Lecture lecture3 = lectureService.createLecture(3, course2.id);
        Lecture lecture4 = lectureService.createLecture(4, course2.id);
        Lecture lecture5 = lectureService.createLecture(5, course2.id);
        Lecture lecture6 = lectureService.createLecture(6, course2.id);
*/

       /* TeacherService teacherService = new TeacherService();

        Teacher teacher1 = teacherService.createTeacher(1);
        Teacher teacher2 = teacherService.createTeacher(2);*/

       // Course course3 = courseService.createCourse(1,1,1,1);


       /* Lecture lecture1 = new Lecture(1, course1.id);
        Lecture lecture2 = new Lecture(2, course1.id);
        Lecture lecture3 = new Lecture(3, course1.id);
        Lecture lecture4 = new Lecture(4, course1.id);
        Lecture lecture5 = new Lecture(5, course1.id);
        Lecture lecture6 = new Lecture(6, course1.id);*/

        Scanner scanner = new Scanner(System.in);
        System.out.println("select a category");
        String category = scanner.nextLine();


        System.out.println(lecture6.idCourse);
        System.out.println(Lecture.counter);
    }
}