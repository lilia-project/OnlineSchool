package org.lilia.repository;

import org.lilia.models.Lecture;

public interface LectureRepository {

    void add(Lecture element);

    void add(int index, Lecture element);

    Lecture remove(int index);

    Lecture getE(int index);

    boolean isEmpty();

    int size();

    void expandingArray();

    Lecture[] getAll();

}




