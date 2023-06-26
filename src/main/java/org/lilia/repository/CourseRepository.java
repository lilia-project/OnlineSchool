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
import java.util.Objects;
import java.util.Optional;

public class CourseRepository extends AbstractRepository {
    private static final List<Course> courseList = new ArrayList<>();

    /*  public static void main(String[] args) {
          Course course = new Course(15, "cust");
          remove(course);
      }*/
    public static void remove(Course course) { // todo its work!
        try {
            final String sql = "DELETE FROM public.course\n" +
                    "WHERE id = " + course.getId();
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

/*
    public static void main(String[] args) {
        int cust =insertValue("'cust'");
        System.out.println("res:" + cust);
    }
*/

    public static void sortByName() { // todo don't work from the console
        try {
            final String sql = "SELECT name FROM public.course ORDER BY name";
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                final List<Course> courses = new ArrayList<>();

                while (resultSet.next()) {
                    Course course = new Course(resultSet.getInt("id"), resultSet.getString("name"));
                    courses.add(course);
                }
                courses.stream()
                        .map(Course::getName)
                        .sorted()
                        .forEach(System.out::println);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
//        throw new IllegalArgumentException();
    }

    //    public static void main(String[] args) throws SQLException {
//        createTableOfCourse();
//    }
    public boolean createTableOfCourse() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = createConnection();
            statement = connection.createStatement();

            final String sql = "CREATE TABLE IF NOT EXIST public.course\n" +
                    "(\n" +
                    " id serial NOT NULL,\n" +
                    " name text NOT NULL,\n" +
                    " PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "ALTER TABLE IF EXISTS public.course\n" +
                    "OWNER to postgres;";

            final boolean execute = statement.execute(sql);
            return execute;

        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        } finally {
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
                if (Objects.nonNull(statement)) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        }
        throw new IllegalArgumentException();
    }


    //    public static Course add(String name) {
//        try {
//            String sltTemplate = "INSERT INTO public.course(name) VALUES(?)";
//
//            try (Connection connection = createConnection();
//                 PreparedStatement statement = connection.prepareStatement(sltTemplate, Statement.RETURN_GENERATED_KEYS)) {
//                statement.setString(1, name);
//                statement.executeUpdate();
//
//                ResultSet generatedKeys = statement.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    return new Course(generatedKeys.getInt(1), name);
//                } else {
//                    throw new SQLException("Creating user failed, no ID obtained.");
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println("Connection failed..." + ex);
//            throw new IllegalArgumentException();
//        }
//    }

    public int insertValue(String name) {// todo don't work from the console
        try {
            final String sql = "INSERT INTO public.course(\n" +
                    "\tname)\n" +
                    "\tVALUES " + "(" + name + ")";

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

    public Optional<Course> getById(final int id) { // todo works from the console but not return Optional.empty
        try {
            String sql = "SELECT * FROM course WHERE id = " + id;
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    Course course = new Course(resultSet.getInt("id"), resultSet.getString("name"));
                    return Optional.of(course);
                }
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

//   public static void main(String[] args) {
//        sortByName();
//   }

    public void getAllCourses() { // todo its work!
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
                courses.forEach(System.out::println); //todo what can I reflect values in service or view level?
            }
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        }
        // throw new IllegalArgumentException();// todo question
    }

    public void serializeCourses() { // todo what can I collect result of the method getAllCourses in a list?

        Serializer.serialize(courseList, FilePath.FILE_PATH_COURSE);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserializeCourses() {

        String filePath = FilePath.FILE_PATH_COURSE.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<Course> courses = (List<Course>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
        courseList.addAll(courses);
    }
}


