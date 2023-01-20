package org.lilia.service;

import org.lilia.models.Homework;
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
        return homeworkRepository.getAll();
    }

    public Homework getById(int homeworkId) {
        System.out.println("you selected to open homework");
        Homework homework = homeworkRepository.getE(homeworkId);
        System.out.println(homework);
        return homework;
    }
}
