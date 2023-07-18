package org.lilia.repository;

import org.lilia.constant.Constants;
import org.lilia.model.Lecture;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class LectureRepository extends ConnectionFactory {

    private static final List<Lecture> list = new ArrayList<>();

    public static List<Lecture> getByCourseId(int courseId) {
        List<Lecture> resList = list.stream()
                .filter(r -> r.getCourseId() == courseId)
                .collect(Collectors.toList());

        return Optional.of(resList).orElse(Collections.emptyList());
    }

    public void insertValue(String name) {
        try {
            final String sql = """
                    INSERT INTO public.lecture(
                    \tname)
                    VALUES(?);""";

            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate(sql);

                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Device: " + rows);
            }
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    private void setFields(ResultSet resultSet, Lecture lecture) throws SQLException {
        lecture.setId(resultSet.getInt("id"));
        lecture.setCreatedAt(LocalDateTime.parse(resultSet.getString("create_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        lecture.setLectureDate(LocalDateTime.parse(resultSet.getString("lecture_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        lecture.setName(resultSet.getString("name"));
        lecture.setCourseId(resultSet.getInt("course_id"));
        lecture.setPersonId(resultSet.getInt("person_id"));
        lecture.setDescription(resultSet.getString("description"));
    }

    public Optional<Lecture> getById(int id) {
        try {
            String sql = "SELECT * FROM public.lecture WHERE id = ?";
            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, id);
                final ResultSet resultSet = preparedStatement.executeQuery();

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

    public List<Lecture> getAllLecture() {
        try {
            final String sql = "SELECT * FROM public.lecture";
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
                return lectures;
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
        return Collections.emptyList();
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

    public void remove(Lecture lecture) {
        try {
            final String sql = """
                    DELETE FROM public.lecture
                    \tWHERE id = ?;""";

            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, lecture.getId());
                preparedStatement.executeQuery();

            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
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

    public void printLecturesGroupingByTeacher() {
        Map<Integer, List<Lecture>> collect = list.stream()
                .collect(Collectors.groupingBy(Lecture::getPersonId));

        System.out.println(collect);
    }
}
