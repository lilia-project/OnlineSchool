package org.lilia.service;

import org.lilia.models.Lecture;
import org.lilia.models.LectureDto;
import org.lilia.models.Model;
import org.lilia.repository.GeneralRepository;

public class LectureService {

    private final GeneralRepository<Lecture> lectureRepository;//указать тип объуктов точно
    //название перем должно отражать что там лежит

    public LectureService(GeneralRepository<Lecture> lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public Lecture createLecture(int courseId, String lectureName, String description, int personId) {
        Lecture lecture = new Lecture(courseId, lectureName, description, personId);
        lectureRepository.add(lecture);
        System.out.println("\nthe lecture has been created: " + lecture);
        return lecture;
    }

    public LectureDto createLectureDto(int courseId, String lectureName, String description, int personId) {
        LectureDto lectureDto = new LectureDto(courseId, lectureName, description, personId);
        return lectureDto;
    }

    public void out() {
        for (Model lecture : lectureRepository.getAll()) {
            System.out.println(lecture.toString());
        }
    }

    public Lecture getById(int lectureId) {
        System.out.println("you selected to open lecture");
        Lecture lecture = lectureRepository.getE(lectureId);
        System.out.println(lecture);
        return lecture;
    }

    public Lecture updateLecture(Lecture lecture, LectureDto lectureDto) {
        if ((lectureDto.getName()) != null) {
            lecture.setName(lectureDto.getName());
        }
        if (lectureDto.getDescription() != null) {
            lecture.setDescription(lectureDto.getDescription());
        }
        if (lectureDto.getCourseId() != 0) { // todo курс id поменчть на Integer в DTO
            lecture.setCourseId(lectureDto.getCourseId());
        }
        if (lectureDto.getTeacherId() != 0) {
            lecture.setPersonId(lectureDto.getTeacherId());
        }
        return lecture;
    }

    public void deleteById(int lectureId) {
        lectureRepository.remove(lectureId);
        System.out.println("Lecture " + lectureId + " has been deleted");
    }

    public int size() {
        return lectureRepository.size();
    }
}