package org.lilia.repository;

import org.lilia.model.Homework;

import java.util.*;

public class HomeworkRepository {

    private final Map<Integer, List<Homework>> data = new HashMap<>();

    public void add(Homework homework) {
        List<Homework> value = data.get(homework.getLectureId());
        if (value == null) {
            data.put(homework.getLectureId(), List.of(homework));// todo repeat
        } else {
            value.add(homework);
        }data.put(homework.getLectureId(), value);
    }


    public void remove(Homework homework) {
        List<Homework> value = data.get(homework.getLectureId());
        if (value == null) {
            return;
        }
        value.remove(homework);
        // data.put(homework.getLectureId(), value);
    }


    public Optional<Homework> getById(int id) {
        Collection<List<Homework>> values = data.values();//список списков дз
        for (List<Homework> homeworks : values) {

            for (Homework homework : homeworks) {

                if (homework.getId() == id) {
                    return Optional.of(homework);
                }
            }

        }
        return Optional.empty();
    }

    public int size() {
        return data.size();
    }

    public void getAll() {
        for (List<Homework> homeworks : data.values()) {

            for (Homework homework : homeworks) {

                System.out.println(homework);
            }
        }
    }

    public Optional<List<Homework>> getByLectureId(int lectureId) {
        List<Homework> list = data.get(lectureId);
        return Optional.ofNullable(list);
    }
}
