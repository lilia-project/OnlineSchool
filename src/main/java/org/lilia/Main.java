package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.models.Student;
import org.lilia.models.Teacher;

public class Main {
    public static void main(String[] args) {

        Course course1 = new Course(1);
        Course course2 = new Course(2);
        Course course6 = new Course(6);

        System.out.println(Course.counter);

        Student student1 = new Student();
        Student student2 = new Student();

        System.out.println(Student.counter);

        Teacher teacher = new Teacher();
        teacher.teacherName = "Bob";
        // System.out.println(teacher.teacherName);

        Lecture lecture1 = new Lecture(1);
        Lecture lecture21 = new Lecture(21);
        Lecture lecture13 = new Lecture(13);
        Lecture lecture54 = new Lecture(54);
        System.out.println(Lecture.counter);

    }
}