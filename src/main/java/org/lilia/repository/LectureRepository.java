package org.lilia.repository;

import org.lilia.models.Lecture;
import org.lilia.models.Model;
import org.lilia.service.LectureService;

public class LectureRepository extends Repository {
    private final static int STANDARD_CAPACITY = 5;
    private final int lengthArray = STANDARD_CAPACITY;

    @Override

    public Lecture[] getAll() {
        Lecture[] resData = (Lecture[]) super.getAll();
        return resData;
    }

    @Override
    public void getBuild(int idEdit) {
        System.out.println("you selected edit (from LectureRepository)");
        super.getBuild(idEdit);


    }

    /*@Override
    public void deleteBuild() {
        System.out.println("You want delete lecture number ");
        super.deleteBuild();

    }*/

    /* @Override
    public void add(Lecture lecture) {
        super.add(lecture);
    }*/
}




