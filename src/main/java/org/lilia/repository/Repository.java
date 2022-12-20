package org.lilia.repository;

import org.lilia.models.Model;

public class Repository {
    private int lengthArray;
    private int size = 0;
    //Model model = ;
   // private int idModel = model.getId();
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
        System.out.println("you selected (from Repository)\n" + model);
    }

   /* public void deleteBuild() {
        int idDelete = model.getId();
        data[idDelete] = null;
        System.out.println("model" + data[idDelete]);
        for (int i = idDelete; i < (lengthArray-1); i++){
            data[i] = data[i+1];
        }
    }*/

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
