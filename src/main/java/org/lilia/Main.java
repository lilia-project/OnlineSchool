package org.lilia;

import org.lilia.models.Course;
import org.lilia.models.Lecture;
import org.lilia.models.Student;
import org.lilia.models.Teacher;

public class Main {
    public static void main(String[] args) {

        Lecture lecture1 = new Lecture(1);
        Lecture lecture2 = new Lecture(2);
        Lecture lecture3 = new Lecture(3);
        Lecture lecture4 = new Lecture(4);
        Lecture lecture5 = new Lecture(5);


        System.out.println(Lecture.counter);

    }
}