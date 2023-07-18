package org.lilia.repository;

import org.lilia.constant.Constants;
import org.lilia.model.Homework;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class HomeworkRepository extends ConnectionFactory {

    public void insertValue(int lectureId, String task) {
        try {
            final String sql = """
                    INSERT INTO public.homework(
                    \ttask, lecture_id)
                    \tVALUES (?,?);""";

            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, task);
                preparedStatement.setInt(2, lectureId);

                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Device: " + rows);

            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    private void setFields(ResultSet resultSet, Homework homework) throws SQLException {
        homework.setId(resultSet.getInt("id"));
        homework.setTask(resultSet.getString("task"));
        homework.setLectureId(resultSet.getInt("lecture_id"));
    }

    public List<Homework> getByLectureId(int lectureId) {
        try {
            String sql = """
                    SELECT * FROM public.homework
                    WHERE lecture_id = ?;""";

            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, lectureId);
                final ResultSet resultSet = preparedStatement.executeQuery();

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

    public Optional<Homework> getById(int id) {
        try {
            String sql = "SELECT * FROM public.homework WHERE id = ? ";
            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, id);
                final ResultSet resultSet = preparedStatement.executeQuery();

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

    public Optional<List<Homework>> getAll() {
        try {
            final String sql = "SELECT * FROM public.homework";
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
            final String sql = "DELETE FROM public.homework\n " +
                    "WHERE id = ?";
            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, homework.getId());
                preparedStatement.execute();

            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
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
