package org.lilia.repository;

import org.lilia.models.Lecture;

public class LectureRepositoryImpl implements LectureRepository {
    private static final int STANDARD_CAPACITY = 5;

    private int lengthArray = STANDARD_CAPACITY;
    private int size = 0;
    private Lecture[] list = new Lecture[lengthArray];

    @Override
    public void add(Lecture element) {
        if (lengthArray <= size) {
            expandingArray();
        }
        list[size] = element;
        size++;
    }

    @Override
    public void expandingArray() {
        lengthArray = lengthArray * 3 / 2 + 1;
        Lecture[] newList = new Lecture[lengthArray];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    @Override
    public void add(int index, Lecture element) {//addition the element by index

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
    public Lecture remove(int index) { //delete by index
        Lecture lecture = list[index];
        for (int i = index; i < (size - 1); i++) {
            list[i] = list[i + 1];
        }
        size--;
        return lecture;
    }

    @Override
    public Lecture getE(int index) { // todo изменить на возвр по index!!!
        for (Lecture lecture : list) {
            if (lecture.getId() == index) {
                return lecture;
            }
        }
        System.out.println("no such index exist");
        return null;
    }

    @Override
    public boolean isEmpty() { // check the array by empty
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
    public Lecture[] getAll() {
        Lecture[] resList = new Lecture[size];
        System.arraycopy(list, 0, resList, 0, size);
        return resList;
    }
}
