package org.lilia.repository;

import org.lilia.model.Person;

public class TeacherRepositoryImpl implements TeacherRepository {

    private static final int STANDARD_CAPACITY = 5;

    private int lengthArray = STANDARD_CAPACITY;
    private int size = 0;
    private Person[] list = new Person[lengthArray];
    @Override
    public void add(Person element) {
        if (lengthArray <= size) {
            expandingArray();
        }
        list[size] = element;
        size++;
    }

    @Override
    public void add(int index, Person element) {
        if (lengthArray <= size) {
            expandingArray();
        }
        if (index == 0 && size == 0) {
            list[index] = element;
        }
        if (index == size) {
            list[index] = element;
        }
        for (int i = (size - 1); i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = element;
        size++;
    }

    @Override
    public Person remove(int index) {

        Person teacher = list[index];
        for (int i = index; i < (size - 1); i++) {
            list[i] = list[i + 1];
        }
        size--;
        return teacher;
    }

    @Override
    public Person getE(int index) {

        for (Person teacher : list) {
            if (teacher.getId() == index) {
                return teacher;
            }
        }
        System.out.println("no such index exist");
        return null;
    }

    @Override
    public boolean isEmpty() {
        boolean b = false;
        if (size == 0) {
            b = true;
        }
        return b;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void expandingArray() {
        lengthArray = lengthArray * 3 / 2 + 1;
        Person[] newList = new Person[lengthArray];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    @Override
    public Person[] getAll() {
        Person[] resList = new Person[size];
        System.arraycopy(list, 0, resList, 0, size);
        return resList;
    }
}
