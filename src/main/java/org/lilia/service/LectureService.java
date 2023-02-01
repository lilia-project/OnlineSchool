package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.exception.NoSuchLectureIdException;
import org.lilia.model.Homework;
import org.lilia.model.Lecture;
import org.lilia.dto.LectureDto;
import org.lilia.repository.LectureRepository;

import java.util.Optional;

public class LectureService {
    private final LectureRepository lectureRepository;
    private final HomeworkService homeworkService;

    public LectureService(LectureRepository lectureRepository, HomeworkService homeworkService) {
        this.lectureRepository = lectureRepository;
        this.homeworkService = homeworkService;
    }

    public void createLecture(String lectureName) {
        if (lectureName == null) {
            throw new IllegalArgumentException("lecture name is null");
        }
        Lecture lecture = new Lecture(lectureName);
        lectureRepository.add(lecture);
        System.out.println("\nthe lecture has been created: " + lecture);
    }

    public LectureDto createLectureDto(int courseId, String lectureName, String description, int personId) {
        return new LectureDto(courseId, lectureName, description, personId);
    }

    public void out() {
        lectureRepository.getAll();
    }

    public Lecture getRequireById(int lectureId) {
        Optional<Lecture> lecture = lectureRepository.getById(lectureId);
        if (lecture.isEmpty()) {
            throw new NoSuchLectureIdException(lectureId);
        }
        addHomeworkIntoLecture(lecture.get());
        System.out.println(lecture.get());
        return lecture.get();
    }

    private void addHomeworkIntoLecture(Lecture lecture) {
        Homework[] homeworks = homeworkService.findAllByLectureId(lecture.getId());
        lecture.setHomeworksList(homeworks);
    }

    public Lecture updateLecture(Lecture lecture, LectureDto lectureDto) {
        if ((lectureDto.getName()) != null) {
            lecture.setName(lectureDto.getName());
        }
        if (lectureDto.getDescription() != null) {
            lecture.setDescription(lectureDto.getDescription());
        }
        if (lectureDto.getCourseId() != 0) {
            lecture.setCourseId(lectureDto.getCourseId());
        }
        if (lectureDto.getTeacherId() != 0) {
            lecture.setPersonId(lectureDto.getTeacherId());
        }
        return lecture;
    }

    public void deleteById(int lectureId) {
        Lecture lecture = getRequireById(lectureId);
        lectureRepository.remove(lecture);
        System.out.println("Lecture " + lectureId + " has been deleted");
    }

    public int size() {
        return lectureRepository.size();
    }

    public int lectureIdIsValid() {
        int lectureId = ConsoleUtils.readInteger();
        Optional<Lecture> lecture = lectureRepository.getById(lectureId);
        while (lecture.isEmpty()) {
            System.out.println("input valid lecture's id");
            lectureId = ConsoleUtils.readInteger();
            lecture = lectureRepository.getById(lectureId);
        }
        return lectureId;
    }
}