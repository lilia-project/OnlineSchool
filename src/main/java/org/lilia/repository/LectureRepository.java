package org.lilia.repository;

import org.lilia.models.Lecture;

public class LectureRepository {

    private final Lecture[] data = new Lecture[10];
    private int size = 0;

    public void add(Lecture lecture) {
        if (size < 10) {
            data[size] = lecture;
            size++;
            System.out.println(lecture.toString());
        } else {
            System.out.println("Array is full, last lecture can not be saved");
        }
    }
}
