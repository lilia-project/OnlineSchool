package org.lilia.service;

import org.lilia.models.Lecture;
import org.lilia.models.Model;
import org.lilia.repository.GeneralRepository;

public class LectureService {

    private final GeneralRepository<Lecture> lectureRepository;//указать тип объуктов точно
    //название перем должно отражать что там лежит

    public LectureService(GeneralRepository<Lecture> lectureRepository) {
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

    public void printById(int lectureId) {
        System.out.println("you selected to open lecture");
        Model lecture = lectureRepository.getE(lectureId);
        System.out.println(lecture);
    }

    public void deleteById(int lectureId) {
        lectureRepository.remove(lectureId);
        System.out.println("Lecture " + lectureId + " has been deleted");
    }
}