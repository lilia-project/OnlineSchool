package org.lilia.service;

import org.lilia.models.Lecture;
import org.lilia.repository.LectureRepository;

public class LectureService {
    private final LectureRepository lectureRepository;// объявление переменной

    public LectureService(LectureRepository lectureRepository) {//конструктор, принимающий ссылку на репо
        this.lectureRepository = lectureRepository;
    }

    public Lecture createLecture(int idCourse, String nameLecture) {
        Lecture lecture = new Lecture(idCourse, nameLecture);
        lectureRepository.add(lecture);
        return lecture;
    }

    public Lecture createLecture(int idCourse) {
        Lecture lecture = new Lecture(idCourse);
        lectureRepository.add(lecture);
        return lecture;
    }

    public void out() {
        for (Lecture lecture : lectureRepository.getAll()) {
            System.out.println("id = " + lecture.id);

        }
    }
}