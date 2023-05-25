package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.LectureDto;
import org.lilia.exception.NoSuchLectureIdException;
import org.lilia.log.Logger;
import org.lilia.log.LoggerFactory;
import org.lilia.model.Lecture;
import org.lilia.repository.LectureRepository;

import java.time.LocalDateTime;
import java.util.Collections;
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
        lectureRepository.addNewLecture(lecture);
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
        lecture.setHomeworkList(homeworkService.findAllByLectureId(lecture.getId()).orElse(Collections.emptyList()));
    }

    public List<Lecture> findAllByCourseId(int courseId) {
        return Optional.of(lectureRepository.getByCourseId(courseId)).orElse(Collections.emptyList());
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
        lectureRepository.serializeLecture();
    }

    public void deserialize() {
        lectureRepository.deserializeLecture();
    }


    public void isBeforeDate(LocalDateTime localDate) {
        lectureRepository.isBeforeDate(localDate);
    }

    public void isAfterDate(LocalDateTime localDate) {
        lectureRepository.isAfterDate(localDate);
    }

    public void isBetweenDates(LocalDateTime localDate, LocalDateTime localDateSecond) {
        lectureRepository.isBetweenDate(localDate, localDateSecond);
    }

    public void getLectureInEarlyTime() {
        lectureRepository.getLectureByEarlyTime().
                ifPresentOrElse(System.out::println, () -> System.out.println("No lecture"));
    }

    public void printLecturesGrouping() {
        lectureRepository.printLecturesGroupingByTeacher();
    }
}