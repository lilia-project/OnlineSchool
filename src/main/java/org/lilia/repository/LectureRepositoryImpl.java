package org.lilia.repository;

import org.lilia.models.Lecture;

import java.util.Iterator;

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

    /**
     * @param lectureId parameter description
     * @return description of options
     */
    @Override
    public Lecture remove(int lectureId) {
        Lecture lecture = getById(lectureId);
        if (lecture == null) {
            return null;
        }
        int index = getIndex(lecture);
        removeByIndex(index);
        return lecture;
    }

    private void removeByIndex(int index) {
        for (int i = index; i < (size - 1); i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;
    }

    @Override
    public int getIndex(Lecture lecture) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (list[i] == lecture) {
                break;
            }
            index++;
        }
        return index;
    }

    @Override
    public Lecture getById(int id) {
        for (int i = 0; i < size(); i++) {
            Lecture lecture = list[i];
            if (lecture.getId() == id) {
                return lecture;
            }
        }
        System.out.println("no such id exist");
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
    public Lecture[] getAll() {
        Lecture[] resList = new Lecture[size];
        System.arraycopy(list, 0, resList, 0, size);
        return resList;
    }

    @Override
    public Iterator<Lecture> iterator() {
        Iterator<Lecture> iterator = new SimpleIteratorImpl();
        return iterator;
    }

    private class SimpleIteratorImpl implements Iterator<Lecture> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            if (index < size) {
                return true;
            }
            return false;
        }


        @Override
        public Lecture next() {
            Lecture currentLecture = list[index];
            index++;
            return currentLecture;
        }

        @Override
        public void remove() {
            index--;
            removeByIndex(index);

        }
    }
}
