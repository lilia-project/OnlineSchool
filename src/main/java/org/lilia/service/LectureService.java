package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.dto.LectureDto;
import org.lilia.entity.Lecture;
import org.lilia.exception.NoSuchLectureIdException;
import org.lilia.log.Logger;
import org.lilia.log.LoggerFactory;
import org.lilia.repository.LectureRepo;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private static final Logger logger = LoggerFactory.getLogger(LectureService.class);
    private final LectureRepo lectureRepo;
    private final HomeworkService homeworkService;

    @Autowired
    public LectureService(LectureRepo lectureRepo, HomeworkService homeworkService) {
        this.lectureRepo = lectureRepo;
        this.homeworkService = homeworkService;
    }

    public List<Lecture> findAllByCourseId(int courseId) {
        return lectureRepo.getByCourseId(courseId);

    }

    public void createLecture(String lectureName) {
        if (lectureName == null) {
            throw new IllegalArgumentException("lecture name is null");
        }
        Lecture lecture = new Lecture();
        lecture.setName(lectureName);
        lecture.setCreatedAt(LocalDateTime.now());
        lecture.setLectureDate(LocalDateTime.now().plusDays((int) (Math.random() * 10)));
        lectureRepo.save(lecture);
        ConsoleUtils.print(Constants.ELEMENT_CREATED);
    }

    public LectureDto createLectureDto(int courseId, String lectureName, String description, int personId) {
        return new LectureDto(courseId, lectureName, description, personId);
    }

    public void updateLecture(Lecture lecture, LectureDto lectureDto) {
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
        lectureRepo.updateLecture(lecture);
    }

    public Optional<List<Lecture>> outputAll() {
        return Optional.of(lectureRepo.findAll());
    }

    public Lecture getRequireById(int lectureId) {
        Optional<Lecture> lecture = lectureRepo.findById(lectureId);
        if (lecture.isEmpty()) {
            throw new NoSuchLectureIdException(lectureId);
        }
        addHomeworkIntoLecture(lecture.get());
        System.out.println(lecture.get());
        return lecture.get();
    }

    private void addHomeworkIntoLecture(Lecture lecture) {
        lecture.setHomeworkList(homeworkService.findAllByLectureId(lecture.getId()));
    }

    public void deleteById(int lectureId) {
        Lecture lecture = getRequireById(lectureId);
        lectureRepo.delete(lecture);
        ConsoleUtils.print(Constants.ELEMENT_DELETED);
    }

    public int lectureIdIsValid() {
        int lectureId = Integer.parseInt(ConsoleUtils.readAndValidationInput(Constants.NUMBER));

        Optional<Lecture> lecture = lectureRepo.findById(lectureId);
        while (lecture.isEmpty()) {
            logger.error("lecture not found by this lectureId");
            logger.info("repeat input");
            lectureId = Integer.parseInt(ConsoleUtils.readAndValidationInput(Constants.NUMBER));
            lecture = lectureRepo.findById(lectureId);
        }
        return lectureId;
    }

    public void backupLecture() {
        List<Lecture> lecture = lectureRepo.findAll();
        Serializer.serialize(lecture, FilePath.FILE_PATH_LECTURE);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserialize() {
        String filePath = FilePath.FILE_PATH_LECTURE.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<Lecture> lectures = (List<Lecture>) deserialize;

        lectures.forEach(System.out::println);
        for (Lecture lecture : lectures) {
            lectureRepo.save(lecture);
        }
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
    }


    public List<Lecture> isBeforeDate(LocalDateTime localDate) {
        return lectureRepo.isBeforeDate(localDate);
    }

    public List<Lecture> isAfterDate(LocalDateTime localDate) {
        return lectureRepo.isAfterDate(localDate);
    }

    public List<Lecture> isBetweenDates(LocalDateTime localDate, LocalDateTime localDateSecond) {
        return lectureRepo.isBetweenDate(localDate, localDateSecond);
    }

    public List<Lecture> getLectureInEarlyTime() {
        return lectureRepo.getLectureByEarlyTime();

    }

    public List<Lecture> printLecturesGrouping() {
        return lectureRepo.getLecturesGroupingByTeacher();
    }
}