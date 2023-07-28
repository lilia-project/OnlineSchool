package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.dto.HomeworkDto;
import org.lilia.entity.Homework;
import org.lilia.exception.NoSuchHomeworkException;
import org.lilia.repository.HomeworkRepo;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeworkService {
    private final HomeworkRepo homeworkRepo;

    @Autowired
    public HomeworkService(HomeworkRepo homeworkRepo) {
        this.homeworkRepo = homeworkRepo;
    }

    public void createHomework(int lectureId, String task) {
        if (task == null) {
            throw new IllegalArgumentException("homework name is null");
        }
        Homework homework = new Homework();
        homework.setLectureId(lectureId);
        homework.setTask(task);
        homeworkRepo.save(homework);
    }

    public HomeworkDto createHomeworkDto(String task) {
        return new HomeworkDto(task);
    }

    public void updateHomework(Homework homework, HomeworkDto homeworkDto) {
        if ((homeworkDto.getTask()) != null) {
            homework.setTask(homeworkDto.getTask());
        }
        homeworkRepo.updateHomework(homework);
    }

    public Homework getRequireById(int homeworkId) {

        Optional<Homework> homework = homeworkRepo.findById(homeworkId);
        if (homework.isEmpty()) {
            throw new NoSuchHomeworkException(homeworkId);
        }
        return homework.get();
    }

    public List<Homework> findAllByLectureId(int lectureId) {
        return homeworkRepo.getByLectureId(lectureId);
    }

    public void deleteById(int homeworkId) {
        Optional<Homework> homework = homeworkRepo.findById(homeworkId);
        if (homework.isEmpty()) {
            ConsoleUtils.print(Constants.ELEMENT_NOT_EXIST);
            throw new NoSuchHomeworkException(homeworkId);
        } else {
            homeworkRepo.delete(homework.get());
            ConsoleUtils.print(Constants.ELEMENT_DELETED);
        }
    }

    public int homeworkIdIsValid() {
        int homeworkId = ConsoleUtils.readInteger();
        Optional<Homework> homework = homeworkRepo.findById(homeworkId);
        while (homework.isEmpty()) {
            ConsoleUtils.print(Constants.ELEMENT_NOT_EXIST);
            homeworkId = ConsoleUtils.readInteger();
            homework = homeworkRepo.findById(homeworkId);
        }
        return homeworkId;
    }

    public void backupHomework() {
        List<Homework> homeworkList = homeworkRepo.findAll();
        Serializer.serialize(homeworkList, FilePath.FILE_PATH_HOMEWORK);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserialize() {
        String filePath = FilePath.FILE_PATH_HOMEWORK.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<Homework> homeworks = (List<Homework>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
        homeworks.forEach(System.out::println);
    }

}


