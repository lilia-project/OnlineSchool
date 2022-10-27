package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Student;

public class Main {
    public static void main(String[] args) {

        Course course1 = new Course();
        Course course2 = new Course();
        Course course6 = new Course();

        System.out.println(Course.counter);

        Student student1 = new Student();
        Student student2 = new Student();

        System.out.println(Student.counter);

    }
}