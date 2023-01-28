package org.lilia.repository;

import org.lilia.models.Lecture;

public interface LectureRepository extends Iterable<Lecture> {

    void add(Lecture element);

    void add(int index, Lecture element);

    Lecture remove(int index);

    Lecture getById(int index);

    int getIndex(Lecture lecture);

    boolean isEmpty();

    int size();

    void expandingArray();

    Lecture[] getAll();
}




