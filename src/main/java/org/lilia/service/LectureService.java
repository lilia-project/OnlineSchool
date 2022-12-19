package org.lilia.service;

import org.lilia.models.Lecture;
import org.lilia.models.Model;
import org.lilia.repository.LectureRepository;
import org.lilia.repository.Repository;

public class LectureService {
   // private final LectureRepository lectureRepository;// todo объявление переменной
   private final Repository repository;

    public LectureService(Repository repository) {
        this.repository = repository;
    }

//    public LectureService(LectureRepository lectureRepository) {// todo конструктор, принимающий ссылку на репо
//        this.lectureRepository = lectureRepository;
//    }

    public Lecture createLecture(int idCourse, String nameLecture) {
        Lecture lecture = new Lecture(idCourse, nameLecture);
        repository.add(lecture);
        System.out.println("Created " + lecture);
        return lecture;
    }

    public void out() {
        for (Model lecture : repository.getAll()) {
            System.out.println("id = " + lecture.getId());

        }
    }
}