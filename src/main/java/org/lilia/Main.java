package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;

public class Main {
    public static void main(String[] args) {

        Course course1 = new Course(1);

        Lecture lecture1 = new Lecture(1, course1.id);
        Lecture lecture2 = new Lecture(2, course1.id);
        Lecture lecture3 = new Lecture(3, course1.id);
        Lecture lecture4 = new Lecture(4, course1.id);
        Lecture lecture5 = new Lecture(5, course1.id);
        Lecture lecture6 = new Lecture(6, course1.id);

        System.out.println(lecture6.idCourse);
        System.out.println(Lecture.counter);
    }
}