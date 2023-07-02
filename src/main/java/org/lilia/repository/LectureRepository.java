package org.lilia.repository;

import org.lilia.constant.Constants;
import org.lilia.model.Lecture;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class LectureRepository extends ConnectionFactory {

    private final List<Lecture> list = new ArrayList<>();

    private static void setFields(ResultSet resultSet, Lecture lecture) throws SQLException {
        lecture.setId(resultSet.getInt("id"));
        lecture.setCreatedAt(LocalDateTime.parse(resultSet.getString("create_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        lecture.setLectureDate(LocalDateTime.parse(resultSet.getString("lecture_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        lecture.setName(resultSet.getString("name"));
        lecture.setCourseId(resultSet.getInt("course_id"));
        lecture.setPersonId(resultSet.getInt("person_id"));
        lecture.setDescription(resultSet.getString("description"));
    }

    public void insertValue(String name) {
        try {
            final String sql = "INSERT INTO public.lecture(\n" +
                    "\tname)\n" +
                    "\tVALUES " + "('" + name + "')";

            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    public void serializeLecture() {
        Serializer.serialize(list, FilePath.FILE_PATH_LECTURE);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserializeLecture() {
        String filePath = FilePath.FILE_PATH_LECTURE.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<Lecture> lectures = (List<Lecture>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);

        list.addAll(lectures);
    }

    public void remove(Lecture lecture) {
        try {
            final String sql = "DELETE FROM public.lecture\n" +
                    "WHERE id = " + lecture.getId();
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeQuery(sql);

            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    public Optional<Lecture> getById(int id) {
        try {
            String sql = "SELECT * FROM lecture WHERE id = " + id;
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    Lecture lecture = new Lecture();
                    setFields(resultSet, lecture);
                    return Optional.of(lecture);
                }
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();

        }
        return Optional.empty();
    }

    public void getAllLecture() {
        try {
            final String sql = "SELECT * FROM lecture";
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                final List<Lecture> lectures = new ArrayList<>();

                while (resultSet.next()) {
                    Lecture lecture = new Lecture();
                    setFields(resultSet, lecture);

                    lectures.add(lecture);
                }
                lectures.forEach(System.out::println);
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    public List<Lecture> getByCourseId(int courseId) {
        List<Lecture> resList = list.stream()
                .filter(r -> r.getCourseId() == courseId)
                .collect(Collectors.toList());

        return Optional.of(resList).orElse(Collections.emptyList());
    }

    public void isBeforeDate(LocalDateTime localDate) {
        list.stream()
                .filter(lecture -> lecture.getLectureDate().isBefore(localDate))
                .forEach(System.out::println);
    }

    public void isAfterDate(LocalDateTime localDate) {
        list.stream()
                .filter(lecture -> lecture.getLectureDate().isAfter(localDate))
                .forEach(System.out::println);
    }

    public void isBetweenDate(LocalDateTime localDate, LocalDateTime localDateSecond) {
        list.stream()
                .filter(lecture -> lecture.getLectureDate().isAfter(localDate))
                .filter(lecture -> lecture.getLectureDate().isBefore(localDateSecond))
                .forEach(System.out::println);
    }

    public void getLectureByEarlyTime() {
        try {
            final String sql = """
                    SELECT *
                    \tFROM public.lecture
                    \tORDER BY create_at;""";

            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                List<Lecture> lectures = new ArrayList<>();
                while (resultSet.next()) {
                    Lecture lecture = new Lecture();
                    setFields(resultSet, lecture);
                    lectures.add(lecture);
                }
                lectures.forEach(System.out::println);
            }
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    public void printLecturesGroupingByTeacher() {
        Map<Integer, List<Lecture>> collect = list.stream()
                .collect(Collectors.groupingBy(Lecture::getPersonId));

        System.out.println(collect);
    }
}
