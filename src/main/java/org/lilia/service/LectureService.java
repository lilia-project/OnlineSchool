package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.LectureDto;
import org.lilia.exception.NoSuchLectureIdException;
import org.lilia.log.Logger;
import org.lilia.log.LoggerFactory;
import org.lilia.model.Homework;
import org.lilia.model.Lecture;
import org.lilia.repository.LectureRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectureService {
    private static final Logger logger = LoggerFactory.getLogger(LectureService.class);
    private final LectureRepository lectureRepository;
    private final HomeworkService homeworkService;

    public LectureService(LectureRepository lectureRepository, HomeworkService homeworkService) {
        this.lectureRepository = lectureRepository;
        this.homeworkService = homeworkService;
    }

    public Lecture createLecture(String lectureName) {
        if (lectureName == null) {
            throw new IllegalArgumentException("lecture name is null");
        }
        Lecture lecture = new Lecture(lectureName);
        lectureRepository.add(lecture);
        ConsoleUtils.print(Constants.ELEMENT_CREATED + lecture);
        return lecture;
    }

    public LectureDto createLectureDto(int courseId, String lectureName, String description, int personId) {
        return new LectureDto(courseId, lectureName, description, personId);
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

    public void outputAll() {
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
        List<Homework> list = homeworkService.findAllByLectureId(lecture.getId());
        lecture.setList(list);
    }

    public List<Lecture> findAllByCourseId(int courseId) {

        List<Lecture> resList = new ArrayList<>();
        Optional<Lecture> lecture;

        for (int i = 0; i < lectureRepository.size(); i++) {
            if (lectureRepository.getByCourseId(courseId).isPresent()) {
                lecture = lectureRepository.getByCourseId(courseId);
                resList.add(lecture.get());
            }
        }
        return resList;
    }

    public void deleteById(int lectureId) {
        Lecture lecture = getRequireById(lectureId);
        lectureRepository.remove(lecture);
        ConsoleUtils.print(Constants.ELEMENT_DELETED);
    }

    public int lectureIdIsValid() {
        int lectureId = Integer.parseInt(ConsoleUtils.readAndValidationInput(Constants.NUMBER));

        Optional<Lecture> lecture = lectureRepository.getById(lectureId);
        while (lecture.isEmpty()) {
            logger.error("lecture not found by this lectureId");
            logger.info("repeat input");
            lectureId = Integer.parseInt(ConsoleUtils.readAndValidationInput(Constants.NUMBER));
            lecture = lectureRepository.getById(lectureId);
        }
        return lectureId;
    }

    public void backupLecture() {
        lectureRepository.serializeList();
    }

    public void deserialize() {
        lectureRepository.deserialize();
    }



    public void isBeforeDate(LocalDate localDate) {
        lectureRepository.isBeforeDate(localDate);
    }

    public void isAfterDate(LocalDate localDate) {
        lectureRepository.isAfterDate(localDate);
    }

    public void isBetweenDates(LocalDate localDate, LocalDate localDateSecond) {
        lectureRepository.isBetweenDate(localDate, localDateSecond);
    }

    public void getLectureInEarlyTimeCreate() {
        lectureRepository.getLectureInEarlyTimeCreate();
    }
}