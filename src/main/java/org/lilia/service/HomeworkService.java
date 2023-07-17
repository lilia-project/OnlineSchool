package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.dto.HomeworkDto;
import org.lilia.entity.Homework;
import org.lilia.exception.NoSuchHomeworkException;
import org.lilia.repository.HomeworkRepository;
import org.lilia.util.ConsoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

    @Autowired
    public HomeworkService(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public void createHomework(int lectureId, String task) {
        if (task == null) {
            throw new IllegalArgumentException("homework name is null");
        }
        Homework homework = new Homework();
        homework.setLectureId(lectureId);
        homework.setTask(task);
        homeworkRepository.save(homework);
    }

    public HomeworkDto createHomeworkDto(String task) {
        return new HomeworkDto(task);
    }

    public Homework updateHomework(Homework homework, HomeworkDto homeworkDto) {
        if ((homeworkDto.getTask()) != null) {
            homework.setTask(homeworkDto.getTask());
        }
        return homework;
    }

    public Homework getRequireById(int homeworkId) {

        Optional<Homework> homework = homeworkRepository.get(homeworkId);
        if (homework.isEmpty()) {
            throw new NoSuchHomeworkException(homeworkId);
        }
        return homework.get();
    }

    public Optional<List<Homework>> findAllByLectureId(int lectureId) {
        List<Homework> homeworkList = homeworkRepository.getByLectureId(lectureId);
        return Optional.ofNullable(homeworkList);
    }

    public void deleteById(int homeworkId) {
        Optional<Homework> homework = homeworkRepository.get(homeworkId);
        if (homework.isEmpty()) {
            ConsoleUtils.print(Constants.ELEMENT_NOT_EXIST);
            throw new NoSuchHomeworkException(homeworkId);
        } else {
            homeworkRepository.delete(homework.get());
            ConsoleUtils.print(Constants.ELEMENT_DELETED);
        }
    }

    public int homeworkIdIsValid() {
        int homeworkId = ConsoleUtils.readInteger();
        Optional<Homework> homework = homeworkRepository.get(homeworkId);
        while (homework.isEmpty()) {
            ConsoleUtils.print(Constants.ELEMENT_NOT_EXIST);
            homeworkId = ConsoleUtils.readInteger();
            homework = homeworkRepository.get(homeworkId);
        }
        return homeworkId;
    }

    public void backupHomework() {
        homeworkRepository.serializeHomework();
    }

    public void deserialization() {
        homeworkRepository.deserialize();
    }
}


