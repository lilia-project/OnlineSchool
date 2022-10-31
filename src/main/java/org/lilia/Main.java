package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.models.Student;
import org.lilia.models.Teacher;

public class Main {
    public static void main(String[] args) {

        Lecture lecture1 = new Lecture(1);
        Lecture lecture21 = new Lecture(21);
        Lecture lecture13 = new Lecture(13);
        Lecture lecture5 = new Lecture(5);

        System.out.println(Lecture.counter);

    }
}