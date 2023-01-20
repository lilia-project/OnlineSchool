package org.lilia.service;

import org.lilia.models.Homework;
import org.lilia.models.Lecture;
import org.lilia.models.LectureDto;
import org.lilia.repository.LectureRepository;

public class LectureService {

    private final LectureRepository lectureRepository;
    private final HomeworkService homeworkService;

    public LectureService(LectureRepository lectureRepository, HomeworkService homeworkService) {
        this.lectureRepository = lectureRepository;
        this.homeworkService = homeworkService;
    }

    public Lecture createLecture(String lectureName) {
        Lecture lecture = new Lecture(lectureName);
        lectureRepository.add(lecture);
        System.out.println("\nthe lecture has been created: " + lecture);
        return lecture;
    }

    public LectureDto createLectureDto(int courseId, String lectureName, String description, int personId) {
        LectureDto lectureDto = new LectureDto(courseId, lectureName, description, personId);
        return lectureDto;
    }

    public void out() {
        for (Lecture lecture : lectureRepository.getAll()) {
            System.out.println(lecture.toString());
        }
    }

    public Lecture getById(int lectureId) {
        Lecture lecture = lectureRepository.getE(lectureId);
        Homework[] homeworks = homeworkService.findAllByLectureId(lectureId);
        lecture.setHomeworksList(homeworks);
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
        if (lectureDto.getCourseId() != 0) { // todo курс id поменять на Integer в DTO
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