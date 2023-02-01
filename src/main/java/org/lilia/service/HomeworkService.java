package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.model.Homework;
import org.lilia.dto.HomeworkDto;
import org.lilia.repository.HomeworkRepository;

public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

    public HomeworkService(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public Homework createHomework(int lectureId, String task, String additionalMaterial) {
        Homework homework = new Homework(lectureId, task, additionalMaterial);
        homeworkRepository.add(homework);
        System.out.println("\n homework has been created");
        return homework;
    }

    public Homework[] findAllByLectureId(int lectureId) {
        Homework[] resList = homeworkRepository.getAll();
        int length = homeworkRepository.size();
        Homework[] lectureIdHomeworks = new Homework[length];
        int count = 0;
        for (Homework current : resList) {
            if (current.getLectureId() == lectureId) {
                lectureIdHomeworks[count] = current;
                count++;
            }
        }
        Homework[] newList = new Homework[count];
        System.arraycopy(lectureIdHomeworks, 0, newList, 0, count); // copy array without null-element
        return newList;
    }

    public Homework getById(int homeworkId) {
        System.out.println("you selected to open homework");
        Homework homework = homeworkRepository.getE(homeworkId);
        System.out.println(homework);
        return homework;
    }

    public int size() {
        return homeworkRepository.size();
    }

    public int homeworkIdIsValid() {
        int homeworkId = ConsoleUtils.readInteger();
        Homework homework = homeworkRepository.getE(homeworkId);
        while (homework == null) {
            System.out.println("input valid homework's id");
            homeworkId = ConsoleUtils.readInteger();
            homework = homeworkRepository.getE(homeworkId);
        }
        return homeworkId;
    }

    public void deleteById(int homeworkId) {
        homeworkRepository.remove(homeworkId);
        System.out.println("homework " + homeworkId + " has been deleted");
    }

    public Homework updateHomework(Homework homework, HomeworkDto homeworkDto) {
        if ((homeworkDto.getTask()) != null) {
            homework.setTask(homeworkDto.getTask());
        }
        if (homeworkDto.getAdditionalMaterial() != null) {
            homework.setAdditionalMaterial(homeworkDto.getAdditionalMaterial());
        }
        return homework;
    }

    public HomeworkDto createHomeworkDto(String task, String additionMaterial) {
        return new HomeworkDto(task, additionMaterial);
    }
}


