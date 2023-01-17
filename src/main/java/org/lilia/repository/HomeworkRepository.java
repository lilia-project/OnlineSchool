package org.lilia.repository;

import org.lilia.models.Homework;

public interface HomeworkRepository {
    void add(Homework element);

    void add(int index, Homework element);

    Homework remove(int index);

    Homework getE(int index);

    boolean isEmpty();

    int size();

    void expandingArray();

    Homework[] getAll();
}
