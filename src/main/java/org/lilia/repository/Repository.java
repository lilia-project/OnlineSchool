package org.lilia.repository;

import org.lilia.models.Model;

import java.util.Arrays;
public class Repository {
    private final static int STANDARD_CAPACITY = 5;

    private int lengthArray = STANDARD_CAPACITY;
    private Model[] data = new Model[lengthArray];
    private int size = 0;

    public void add(Model model) {
        if (lengthArray <= size) {
            expandingArray();
        }
        data[size] = model;
        size++;
    }

    private void expandingArray() {
        lengthArray = lengthArray * 3 / 2 + 1;
        Model[] newData = new Model[lengthArray];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public Model[] getAll() {
        Model[] resData = new Model[size];
        System.arraycopy(data, 0, resData, 0, size);
        System.out.println(Arrays.toString(resData));
        return resData;
    }

    public void getById(int lectureId) {
        Model model = data[lectureId];
        System.out.println(model); //todo
    }

    public Model[] deleteById(int lectureId) {
        Model model = data[lectureId];
        System.out.println(model);
        for (int i = lectureId; i < (size - 1); i++) {
            data[i] = data[i + 1];
        }
        size--;
        return getData();
    }

    public Model[] getData() {
        return data;
    }

    public int getLengthArray() {
        return lengthArray;
    }

    public void setLengthArray(int lengthArray) {
        this.lengthArray = lengthArray;
    }

    public int getSize() {
        return size;
    }
}
