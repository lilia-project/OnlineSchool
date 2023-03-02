package org.lilia.repository;

import org.lilia.model.Lecture;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectureRepository {

    private final List<Lecture> list = new ArrayList<>();

    public void add(Lecture lecture) {
        list.add(lecture);
    }

    public void serializeList() {
        for (Lecture lecture : list) {
            Serializer.serialize(lecture, FilePath.FILE_PATH_LECTURE);
        }
    }

    public void deserialize() {
        String filePath = FilePath.FILE_PATH_LECTURE.getPath();
        System.out.println(Serializer.deserialize(filePath));
    }

    public void remove(Lecture lecture) {
        list.remove(lecture);
    }


    public Optional<Lecture> getById(int id) {
        for (Lecture lecture : list) {
            if (lecture.getId() == id) {
                return Optional.of(lecture);
            }
        }
        return Optional.empty();
    }

    public int size() {
        return list.size();
    }

    public void getAll() {
        for (Lecture lecture : list) {
            System.out.println(lecture);
        }
    }

    public Optional<Lecture> getByCourseId(int courseId) {
        for (Lecture lecture : list) {
            if (lecture.getCourseId() == courseId) {
                return Optional.of(lecture);
            }
        }
        return Optional.empty();
    }


}
