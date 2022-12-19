package org.lilia.repository;

import org.lilia.models.Lecture;
import org.lilia.service.LectureService;

public class LectureRepository {
    private final static int STANDARD_CAPACITY = 5;
    private int lengthArray = STANDARD_CAPACITY; //todo
    public Lecture[] data = new Lecture[lengthArray];
    private int size = 0;

    public void add(Lecture lecture) {
        if (lengthArray <= size) { //todo extract method
            lengthArray = lengthArray * 3 / 2 + 1;
            Lecture[] newData = new Lecture[lengthArray];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        data[size] = lecture;
        size++;
    }



    public Lecture[] getAll() { //todo ?
        Lecture[] resData = new Lecture[size];
        for (int i = 0; i < size; i++) {
            resData[i] = data[i];
        }
        return resData;
    }
}




