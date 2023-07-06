package org.lilia.repository;

import org.lilia.constant.Constants;
import org.lilia.model.Course;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository extends ConnectionFactory {

    public static void sortByName() {
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

    public void remove(Course course) {
        try {
            final String sql = "DELETE FROM public.course\n" +
                    "WHERE id = " + course.getId();
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

    public int insertValue(String name) {
        try {
            final String sql = "INSERT INTO public.course(\n" +
                    "\tname)\n" +
                    "\tVALUES " + "('" + name + "')";

            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final int i = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                if (i > 0) {
                    final ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        final int anInt = generatedKeys.getInt(1);
                        System.out.println(anInt);
                    }
                }
                return i;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }

    public Optional<Course> getById(final int id) {
        try {
            String sql = "SELECT * FROM course WHERE id = " + id;
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    Course course = new Course(resultSet.getInt("id"), resultSet.getString("name"));
                    return Optional.of(course);
                }
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    public Optional<List<Course>> getAllCourses() {
        try {
            final String sql = "SELECT * FROM course";
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                final List<Course> courses = new ArrayList<>();

                while (resultSet.next()) {
                    Course course = new Course(resultSet.getInt("id"), resultSet.getString("name"));
                    courses.add(course);
                }
                return Optional.of(courses);
            }
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        }
        return Optional.empty();
    }

    public void updateCourse(int id, String name) {

        final String sql = "UPDATE course\n SET name = " + "('" + name + "')" + "WHERE id = " + "('" + id + "')";

        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(sql);

        } catch (SQLException ex) {
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


