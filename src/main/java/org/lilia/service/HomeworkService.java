package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.HomeworkDto;
import org.lilia.exception.NoSuchHomeworkException;
import org.lilia.model.Homework;
import org.lilia.repository.HomeworkRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

    public HomeworkService(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public void createHomework(int lectureId, String task) {
        if (task == null) {
            throw new IllegalArgumentException("homework name is null");
        }
        Homework homework = new Homework(lectureId, task);
        homeworkRepository.add(homework);
        ConsoleUtils.print(Constants.ELEMENT_CREATED);
    }

    public HomeworkDto createHomeworkDto(String task, String additionMaterial) {
        return new HomeworkDto(task, additionMaterial);
    }

    public Homework updateHomework(Homework homework, HomeworkDto homeworkDto) {
        if ((homeworkDto.getTask()) != null) {
            homework.setTask(homeworkDto.getTask());
        }
        return homework;
    }

    public void out() {
        homeworkRepository.getAll();
    }

    public Homework getRequireById(int homeworkId) {

        Optional<Homework> homework = homeworkRepository.getById(homeworkId);
        if (homework.isEmpty()) {
            throw new NoSuchHomeworkException(homeworkId);
        }
        System.out.println(homework.get());
        return homework.get();
    }

    public List<Homework> findAllByLectureId(int lectureId) {
        Optional<List<Homework>> byLectureId = homeworkRepository.getByLectureId(lectureId);
        if (byLectureId.isPresent()) {
            return byLectureId.get();
        } else {
            return Collections.emptyList();
        }
    }

    public void deleteById(int homeworkId) {
        Homework homework = getRequireById(homeworkId);
        homeworkRepository.remove(homework);
        ConsoleUtils.print(Constants.ELEMENT_DELETED);
    }

    public int homeworkIdIsValid() {
        int homeworkId = ConsoleUtils.readInteger();
        Optional<Homework> homework = homeworkRepository.getById(homeworkId);
        while (homework.isEmpty()) {
            System.out.println("no such homeworkId exist");
            homeworkId = ConsoleUtils.readInteger();
            homework = homeworkRepository.getById(homeworkId);
        }
        return homeworkId;
    }
}


