package org.lilia.repository;

import org.lilia.models.Model;

public class GeneralRepository<E extends Model> {
    private final static int STANDARD_CAPACITY = 5;

    private int lengthArray = STANDARD_CAPACITY;

    public int getSize() {
        return size;
    }

    private int size = 0;
    private Model[] list = new Model[lengthArray];//general array

    public void add(E element) { //addition the element by end of array
        if (lengthArray <= size) {
            expandingArray();
        }
        list[size] = element;
        size++;
    }

    public void add(int index, E element) { //addition the element by index

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

    public E remove(int index) { //delete by index
        Model model = list[index];
        for (int i = index; i < (size - 1); i++) {
            list[i] = list[i + 1];
        }
        size--;
        return (E) model;
    }

    private void expandingArray() {
        lengthArray = lengthArray * 3 / 2 + 1;
        Model[] newList = new Model[lengthArray];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    public E getE(int index) { // todo изменить на возвр по index!!!
        for (Model model : list) {
            if (model.getId() == index) {
                return (E) model;
            }
        }
        System.out.println("no such index exist");
        return null;
    }

    public Model[] getAll() {
        Model[] resList = new Model[size];
        System.arraycopy(list, 0, resList, 0, size);
        return resList;
    }

    public boolean isEmpty() { // check the array by empty
        boolean b = false;
        if (size == 0) {
            b = true;
        }
        return b;
    }

    public int size() {
        return size;
    }
}
