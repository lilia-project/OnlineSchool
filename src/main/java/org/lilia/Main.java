package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.models.Student;
import org.lilia.models.Teacher;

public class Main {
    public static void main(String[] args) {

        Course course = new Course(1);

        Lecture lecture1 = new Lecture(1, 1);
        Lecture lecture2 = new Lecture(2, 4);
        Lecture lecture3 = new Lecture(3, 4);
        Lecture lecture4 = new Lecture(4, 5);
        Lecture lecture5 = new Lecture(5, 3);
        Lecture lecture6 = new Lecture(6, 2);

        System.out.println(lecture6.courseId);
        System.out.println(Lecture.counter);


    }
}