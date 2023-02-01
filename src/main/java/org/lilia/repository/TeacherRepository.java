package org.lilia.repository;

import org.lilia.model.Person;

public interface TeacherRepository{
    void add(Person element);

    void add(int index, Person element);

    Person remove(int index);

    Person getE(int index);

    boolean isEmpty();

    int size();

    void expandingArray();

    Person[] getAll();
}
