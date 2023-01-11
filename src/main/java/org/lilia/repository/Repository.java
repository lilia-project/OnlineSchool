package org.lilia.repository;

import org.lilia.models.Model;

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

    public Model[] deleteById(int id) {
        Model model = data[id];
        System.out.println(model);
        for (int i = id; i < (size - 1); i++) {
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
