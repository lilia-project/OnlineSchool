package org.lilia.service;

import org.lilia.models.Lecture;
import org.lilia.models.Model;
import org.lilia.repository.LectureRepository;

public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {// todo конструктор, принимающий ссылку на репо
        this.lectureRepository = lectureRepository;
    }

    public Lecture createLecture(int idCourse, String nameLecture, int personId, String description) {
        Lecture lecture = new Lecture(idCourse, nameLecture, personId, description);
        lectureRepository.add(lecture);
        System.out.println("\nthe lecture has been created: " + lecture);
        return lecture;
    }

    public void out() {
        for (Model lecture : lectureRepository.getAll()) {
            System.out.println(lecture.toString());
        }
    }

    public void getById(int lectureId) {
        lectureRepository.getById(lectureId);
    }

    public void deleteById(int lectureId) {
        lectureRepository.deleteById(lectureId);
        System.out.println("Lecture " + lectureId + " has been deleted");
    }
}