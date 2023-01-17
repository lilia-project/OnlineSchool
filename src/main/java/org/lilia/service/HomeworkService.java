package org.lilia.service;

import org.lilia.models.Homework;
import org.lilia.models.Lecture;
import org.lilia.repository.HomeworkRepository;
import org.lilia.repository.HomeworkRepositoryImpl;

public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

    public HomeworkService(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public Homework createHomework(int lectureId, String task, String additionalMaterial){
        Homework homework = new Homework(lectureId, task, additionalMaterial);
        homeworkRepository.add(homework);
        System.out.println("\n homework has been created" + homework);
        return homework;
    }
}
