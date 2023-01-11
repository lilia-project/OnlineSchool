package org.lilia.repository;

import org.lilia.models.Model;

public class LectureRepository extends Repository {

    @Override
    public Model[] deleteById(int id) {
        System.out.println("You want delete ");
        super.deleteById((id - 1));
        return getData();
    }
}




