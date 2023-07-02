package org.lilia.repository;

import org.lilia.constant.Constants;
import org.lilia.model.Course;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository extends ConnectionFactory {

    public int insertValue(String name) {
        try {
            final String sql = """
                    INSERT INTO public.course(
                    \tname)
                    \tVALUES (?);""";

            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    final int anInt = generatedKeys.getInt(1);
                    return anInt;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
            throw new IllegalArgumentException();
        }
    }

    public Optional<Course> getById(final int id) {
        try {
            String sql = "SELECT * FROM public.course WHERE id = ?";
            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, id);
                final ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Course course = new Course(resultSet.getInt("id"), resultSet.getString("name"));

                    return Optional.of(course);
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

    public Optional<List<Course>> getAllCourses() {
        try {
            final String sql = "SELECT * FROM public.course";
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {

                final ResultSet resultSet = statement.executeQuery(sql);

                final List<Course> courses = new ArrayList<>();

                while (resultSet.next()) {
                    Course course = new Course(resultSet.getInt("id"), resultSet.getString("name"));
                    courses.add(course);
                }
                return Optional.of(courses);
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    public void updateCourse(int id, String name) {
        try {
            final String sql = """
                    UPDATE public.course
                    SET name = ?
                    WHERE id = ?;""";

            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.executeQuery();

            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    public void remove(Course course) {
        try {
            final String sql = "DELETE FROM public.course\n" +
                    "WHERE id = ?";
            try (Connection connection = createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, course.getId());
                preparedStatement.executeQuery();

            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    public void sortByName() {
        final String sql = "SELECT name FROM public.course ORDER BY name";
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);

            final List<String> names = new ArrayList<>();

            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                names.add(courseName);
            }
            names.forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public void serializeCourses() {
        List<Course> courseList = getAllCourses().get();
        Serializer.serialize(courseList, FilePath.FILE_PATH_COURSE);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserializeCourses() {
        String filePath = FilePath.FILE_PATH_COURSE.getPath();
        Serializer.deserialize(filePath);
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
    }

}


