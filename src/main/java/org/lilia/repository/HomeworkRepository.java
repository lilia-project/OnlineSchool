package org.lilia.repository;

import org.lilia.model.Homework;

import java.util.*;

public class HomeworkRepository {

    private final Map<Integer, List<Homework>> data = new HashMap<>();

    public void add(Homework homework) {
        List<Homework> value = data.get(homework.getLectureId());
        if (value == null) {
            value = new ArrayList<>();
            value.add(homework);
            data.put(homework.getLectureId(), value);// todo repeat
        } else {
            value.add(homework);
        }
    }

    public void remove(Homework homework) {
        List<Homework> value = data.get(homework.getLectureId());
        if (value == null) {
            return;
        }
        value.remove(homework);
    }

    public Optional<Homework> getById(int id) {
        Collection<List<Homework>> values = data.values();
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

    public void getAll(int lectureId) {
        Optional<List<Homework>> list = getByLectureId(lectureId);
        if (list.isEmpty()) {
            System.out.println("in lecture " + lectureId + "homework not exist");
        } else {
            List<Homework> resList = list.get();

            for (Homework homework : resList) {
                System.out.println(homework);
            }
        }
    }

    public Optional<List<Homework>> getByLectureId(int lectureId) {
        List<Homework> list = data.get(lectureId);
        return Optional.ofNullable(list);
    }
}
