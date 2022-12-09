package org.lilia.repository;

import org.lilia.models.Lecture;

public class LectureRepository {

    private final Lecture[] data = new Lecture[2];
    private int size = 0;

    public void add(Lecture lecture) {
        if (size < 2) {
            data[size] = lecture;
            size++;
            System.out.println("Created " + lecture.toString());
        } else {
            System.out.println("Array is full, lecture can not be saved");
        }
    }
}
