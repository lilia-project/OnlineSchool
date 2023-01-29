package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.exception.NoSuchLectureIdException;
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
        if (lectureName == null) {
            throw new IllegalArgumentException("lecture name is null");
        }
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
       /* Iterator<Lecture> simpleIterator = lectureRepository.iterator();
        while (simpleIterator.hasNext()) {
            Lecture lecture = simpleIterator.next();
            addHomeworkIntoLecture(lecture);
            System.out.println(lecture);
        }*/
        for (Lecture lecture : lectureRepository) {
            addHomeworkIntoLecture(lecture);
            System.out.println(lecture);
        }
    }

    public Lecture printAndGetById(int lectureId) throws NoSuchLectureIdException {
        Lecture lecture = lectureRepository.getById(lectureId);
        if (lecture == null) {
            throw new NoSuchLectureIdException(lectureId);
        }
        addHomeworkIntoLecture(lecture);
        System.out.println(lecture);
        return lecture;
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

    public int lectureIdIsValid() {
        int lectureId = ConsoleUtils.readInteger();
        Lecture lecture = lectureRepository.getById(lectureId);
        while (lecture == null) {
            System.out.println("input valid lecture's id");
            lectureId = ConsoleUtils.readInteger();
            lecture = lectureRepository.getById(lectureId);
        }
        return lectureId;
    }
}