package org.lilia.repository;

import org.lilia.models.Model;

public class LectureRepository extends Repository {

    @Override
    public void getById(int lectureId) {
        System.out.println("you selected to open lecture");
        super.getById((lectureId - 1));
    }

    @Override
    public Model[] deleteById(int lectureId) {
        System.out.println("You want delete ");
        super.deleteById((lectureId - 1));
        return getData();
    }
}




