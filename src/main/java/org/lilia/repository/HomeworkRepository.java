package org.lilia.repository;

import org.lilia.model.Homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomeworkRepository {

    private final List<Homework> list = new ArrayList<>();

    public void add(Homework homework) {
        list.add(homework);
    }

    public void remove(Homework homework) {
        list.remove(homework);
    }

    public Optional<Homework> getById(int id) {
        for (Homework homework : list) {
            if (homework.getId() == id) {
                return Optional.of(homework);
            }
        }
        return Optional.empty();
    }

    public int size() {
        return list.size();
    }

    public void getAll() {
        for (Homework homework : list) {
            System.out.println(homework);
        }
    }

    public Optional<Homework> getByLectureId(int lectureId) {
        for (Homework homework : list) {
            if (homework.getLectureId() == lectureId) {
                return Optional.of(homework);
            }
        }
        return Optional.empty();
    }
}
