package org.lilia.service;

import org.lilia.models.Lecture;

public class LectureService {
    public Lecture createLecture(int idCourse, String nameLecture) {
        return new Lecture(idCourse, nameLecture);
    }
}
