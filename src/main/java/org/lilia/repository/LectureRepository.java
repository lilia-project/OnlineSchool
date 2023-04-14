package org.lilia.repository;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.model.Lecture;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LectureRepository {

    private final List<Lecture> list = new ArrayList<>();

    public void add(Lecture lecture) {
        list.add(lecture);
    }

    public void serializeList() {
        Serializer.serialize(list, FilePath.FILE_PATH_LECTURE);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserialize() {
        String filePath = FilePath.FILE_PATH_LECTURE.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<Lecture> lectures = (List<Lecture>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);

        for (Lecture lecture : lectures) {
            saveLecture(lecture);
        }
    }

    private void saveLecture(Lecture lecture) {
        for (Lecture currentLecture: list){
            if (currentLecture.getId() == lecture.getId()){
                ConsoleUtils.print(lecture.getId() + " - this lecture id already exists");
                break;
            }
        }
        list.add(lecture);
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
        list.forEach(System.out::println);
    }

    public Optional<Lecture> getByCourseId(int courseId) {
        for (Lecture lecture : list) {
            if (lecture.getCourseId() == courseId) {
                return Optional.of(lecture);
            }
        }
        return Optional.empty();
    }

    public void isBeforeDate(LocalDate localDate) {
        list.stream()
                .filter(lecture -> lecture.getLectureDate().isBefore(localDate))
                .forEach(System.out::println);
    }

    public void isAfterDate(LocalDate localDate) {
        list.stream()
                .filter(lecture -> lecture.getLectureDate().isAfter(localDate))
                .forEach(System.out::println);
    }

    public void isBetweenDate(LocalDate localDate, LocalDate localDateSecond) {
        list.stream()
                .filter(lecture -> lecture.getLectureDate().isAfter(localDate))
                .filter(lecture -> lecture.getLectureDate().isBefore(localDateSecond))
                .forEach(System.out::println);
    }
    public void getLectureInEarlyTimeCreate(){ // todo Lecture output!!!
        deserialize();
        System.out.println(list.stream()
                .min(new Lecture.LectureCreateAtComparator())
                .stream().toList());

    }

    public void getLectureWithMaxAddMaterials(){ // todo Lecture output!!!
        deserialize();

    }

}
