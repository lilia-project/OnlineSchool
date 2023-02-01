package org.lilia.repository;

import org.lilia.model.Homework;

public interface HomeworkRepository {
    void add(Homework element);

    void add(int index, Homework element);

    Homework remove(int index);

    Homework getE(int index);

    Homework[] getAll();

    boolean isEmpty();

    int size();

    void expandingArray();

    Homework getByLectureId(int lectureId);
}
