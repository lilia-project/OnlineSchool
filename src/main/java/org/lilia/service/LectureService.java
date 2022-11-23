package org.lilia.service;

import org.lilia.models.Lecture;

public class LectureService {
    public Lecture createLecture(int id, int idCourse){
        return new Lecture(id,idCourse);
    }
}
