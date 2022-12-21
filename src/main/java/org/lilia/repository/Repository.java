package org.lilia.repository;

import org.lilia.models.Model;

public class Repository {
    private final static int STANDARD_CAPACITY = 5;

    private int lengthArray = STANDARD_CAPACITY;
    private int size = 0;
    private Model[] data = new Model[lengthArray];

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
        return resData;
    }

    public void getBuild(int idEdit) {
        Model model = data[idEdit];
        System.out.println(model);
    }

    public Model[] deleteBuild(int idDelete) {
        Model model = data[idDelete];
        System.out.println(model);
        for (int i = idDelete; i < (size - 1); i++) {
            data[i] = data[i + 1];
        }
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

}
