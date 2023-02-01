package org.lilia.repository;

import org.lilia.model.Course;

public class CourseRepositoryImpl implements CourseRepository {
    private static final int STANDARD_CAPACITY = 5;

    private int lengthArray = STANDARD_CAPACITY;
    private int size = 0;
    private Course[] list = new Course[lengthArray];

    @Override
    public void add(Course element) {
        if (lengthArray <= size) {
            expandingArray();
        }
        list[size] = element;
        size++;
    }

    @Override
    public void add(int index, Course element) {
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
    public Course remove(int index) {
        Course course = list[index];
        for (int i = index; i < (size - 1); i++) {
            list[i] = list[i + 1];
        }
        size--;
        return course;
    }

    @Override
    public Course getE(int index) {
        for (Course course : list) {
            if (course.getId() == index) {
                return course;
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
        Course[] newList = new Course[lengthArray];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    @Override
    public Course[] getAll() {
        Course[] resList = new Course[size];
        System.arraycopy(list, 0, resList, 0, size);
        return resList;
    }
}
