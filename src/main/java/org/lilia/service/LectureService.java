package org.lilia.service;

import org.lilia.models.Lecture;
import org.lilia.repository.LectureRepository;

public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public Lecture createLecture(int idCourse, String nameLecture) {
        Lecture lecture = new Lecture(idCourse, nameLecture);
        lectureRepository.add(lecture);
        return lecture;
    }
}
