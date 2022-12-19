package org.lilia.repository;

import org.lilia.models.Model;

public class Repository {
    private int lengthArray;
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

    public Model[] getAll() { //todo ?
        Model[] resData = new Model[size];
        System.arraycopy(data, 0, resData, 0, size);
        return resData;
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
