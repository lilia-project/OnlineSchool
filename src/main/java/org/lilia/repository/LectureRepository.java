package org.lilia.repository;

import org.lilia.models.Model;

public class LectureRepository extends Repository {

    @Override
    public void getBuild(int idOpen) {
        System.out.println("you selected to open lecture");
        super.getBuild((idOpen - 1));
    }

    @Override
    public Model[] deleteBuild(int idDelete) {
        System.out.println("You want delete ");
        super.deleteBuild((idDelete - 1));
        return getData();
    }
}




