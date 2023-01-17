package org.lilia.repository;

import org.lilia.models.Homework;

public class HomeworkRepositoryImpl implements HomeworkRepository {
    private static final int STANDARD_CAPACITY = 5;

    private int lengthArray = STANDARD_CAPACITY;
    private int size = 0;
    private Homework[] list = new Homework[lengthArray];

    @Override
    public void add(Homework element) {
        if (lengthArray <= size) {
            expandingArray();
        }
        list[size] = element;
        size++;
    }

    @Override
    public void add(int index, Homework element) {
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
    public Homework remove(int index) {
        Homework homework = list[index];
        for (int i = index; i < (size - 1); i++) {
            list[i] = list[i + 1];
        }
        size--;
        return homework;
    }

    @Override
    public Homework getE(int index) {
        for (Homework homework : list) {
            if (homework.getId() == index) {
                return homework;
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
        Homework[] newList = new Homework[lengthArray];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    @Override
    public Homework[] getAll() {
        Homework[] resList = new Homework[size];
        System.arraycopy(list, 0, resList, 0, size);
        return resList;
    }
}
