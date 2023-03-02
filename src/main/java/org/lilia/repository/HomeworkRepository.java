package org.lilia.repository;

import org.lilia.model.Homework;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;

import java.util.*;

public class HomeworkRepository {

    private final Map<Integer, List<Homework>> data = new HashMap<>();

    public void add(Homework homework) {
        List<Homework> value = data.get(homework.getLectureId());
        if (value == null) {
            value = new ArrayList<>();
            value.add(homework);
            data.put(homework.getLectureId(), value);
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

    public Optional<List<Homework>> getByLectureId(int lectureId) {
        List<Homework> list = data.get(lectureId);
        return Optional.ofNullable(list);
    }

    public void serialiseHomework(int lectureId) {
        List<Homework> list = data.get(lectureId);
        for (Homework homework : list) {
            Serializer.serialize(homework, FilePath.FILE_PATH_HOMEWORK);
        }
    }

    public void deserialize() {
        String filePath = FilePath.FILE_PATH_HOMEWORK.getPath();
        System.out.println(Serializer.deserialize(filePath));
    }
}
