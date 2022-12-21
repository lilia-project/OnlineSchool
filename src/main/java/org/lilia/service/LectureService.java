package org.lilia.service;

import org.lilia.models.Lecture;
import org.lilia.models.Model;
import org.lilia.repository.LectureRepository;

public class LectureService {
    private final LectureRepository lectureRepository;// todo объявление переменной

    public LectureService(LectureRepository lectureRepository) {// todo конструктор, принимающий ссылку на репо
        this.lectureRepository = lectureRepository;
    }

    public Lecture createLecture(int idCourse, String nameLecture) {
        Lecture lecture = new Lecture(idCourse, nameLecture);
        lectureRepository.add(lecture);
        System.out.println("Created " + lecture);
        return lecture;
    }

    public void out() {
        for (Model lecture : lectureRepository.getAll()) {
            System.out.println("id = " + lecture.getId());
        }
    }

    public int openLecture(int idOpen) {
        lectureRepository.getBuild(idOpen);
        return idOpen;
    }

    public int deleteLecture(int idDelete) {
        lectureRepository.deleteBuild(idDelete);

        return idDelete;
    }
}