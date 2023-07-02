package org.lilia.repository;

import org.lilia.constant.Constants;
import org.lilia.model.Homework;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HomeworkRepository extends ConnectionFactory {

    public static void insertValue(int lectureId, String task) {
        try {
            final String sql = "INSERT INTO public.homework(\n" +
                    "\ttask, lecture_id)\n" +
                    "\tVALUES ('" + task + "'," + lectureId + ");";

            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    private static void setFields(ResultSet resultSet, Homework homework) throws SQLException {
        homework.setId(resultSet.getInt("id"));
        homework.setTask(resultSet.getString("task"));
        homework.setLectureId(resultSet.getInt("lecture_id"));
    }

    public Optional<List<Homework>> getAll() {
        try {
            final String sql = "SELECT * FROM homework";
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                final List<Homework> homeworkList = new ArrayList<>();

                while (resultSet.next()) {
                    Homework homework = new Homework();
                    setFields(resultSet, homework);

                    homeworkList.add(homework);
                }
                homeworkList.forEach(System.out::println);
            }
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        }
        return Optional.empty();
    }

    public void remove(Homework homework) {
        try {
            final String sql = "DELETE FROM public.homework\n" +
                    "WHERE id = " + homework.getId();
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

    public Optional<Homework> getById(int id) {
        try {
            String sql = "SELECT * FROM homework WHERE id = " + id;
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    Homework homework = new Homework();
                    setFields(resultSet, homework);
                    return Optional.of(homework);
                }
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    public List<Homework> getByLectureId(int lectureId) {
        try {
            String sql = "SELECT * FROM homework\n WHERE lecture_id = " + lectureId;

            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);
                List<Homework> homeworkList = new ArrayList<>();

                while (resultSet.next()) {
                    Homework homework = new Homework();
                    setFields(resultSet, homework);
                    homeworkList.add(homework);
                }
                return homeworkList;

            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
        return Collections.emptyList();
    }

    public void serializeHomework() {
        if (getAll().isPresent()) {
            List<Homework> homeworkList = getAll().get();
            Serializer.serialize(homeworkList, FilePath.FILE_PATH_HOMEWORK);
            ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
        } else {
            ConsoleUtils.print(Constants.ELEMENTS_NOT_EXIST);
        }
    }

    public void deserialize() {
        String filePath = FilePath.FILE_PATH_HOMEWORK.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<Homework> homeworks = (List<Homework>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
        homeworks.forEach(System.out::println);
    }
}
