package org.lilia.repository;

import org.lilia.constant.Constants;
import org.lilia.model.Person;
import org.lilia.model.Role;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class PersonRepository extends ConnectionFactory {
    private final List<Person> personList = new ArrayList<>();

    public static void remove(String lastName) {
        try {
            final String sql = "DELETE FROM public.person\n" +
                    "WHERE last_name = " + lastName;

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

    public static Optional<Person> getByLastName(String lastName) {
        try {
            String sql = "SELECT * FROM person\n WHERE last_name = " + lastName;

            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                Person person = new Person();
                setFields(resultSet, person);

                return Optional.of(person);
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }

        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    private static void setFields(ResultSet resultSet, Person person) throws SQLException {
        person.setId(resultSet.getInt("id"));
        person.setRole(Role.valueOf(resultSet.getString("role")));
        person.setLastName(resultSet.getString("last_name"));
        person.setFirstName(resultSet.getString("first_name"));
        person.setPhone(resultSet.getString("phone"));
        person.setEmail(resultSet.getString("email"));
        person.setCourseId(resultSet.getInt("course_id"));
    }

    private static FilePath getPath(Role role) {

        FilePath filePath;
        if (role.getField().equals("TEACHER")) {
            filePath = FilePath.FILE_PATH_TEACHER;
        } else {
            filePath = FilePath.FILE_PATH_STUDENT;
        }
        return filePath;
    }

    private static List<Person> splitListOfPerson(List<Person> data, Role role) {
        List<Person> splitList = data.stream()
                .filter(person -> person.getRole().equals(role))
                .toList();

        return Optional.of(splitList).orElse(Collections.emptyList());
    }

    public void insertValue(Role role, String last_name) {
        try {
            final String sql = "INSERT INTO public.person(\n" +
                    "\trole, last_name)\n" +
                    "\tVALUES " + "('" + role.getField() + "', '" + last_name + "')";

            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);

            } catch (Exception ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    public Optional<Person> getById(final int id) {
        try {
            String sql = "SELECT * FROM person WHERE id = " + id;
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    Person person = new Person();
                    setFields(resultSet, person);
                    return Optional.of(person);
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

    public void getByCourseId(int courseId, Role role) {
        try {
            String sql = "SELECT * FROM person\n " +
                    "WHERE course_id = '" + courseId + "' " +
                    "AND role = " + "'" + role.getField() + "'";

            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);
                List<Person> people = new ArrayList<>();

                while (resultSet.next()) {
                    Person person = new Person();
                    setFields(resultSet, person);
                    people.add(person);
                }
                people.forEach(System.out::println);

            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
    }

    public List<Person> getAllPerson() {
        try {
            final String sql = "SELECT * FROM person";
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                final List<Person> people = new ArrayList<>();

                while (resultSet.next()) {
                    Person person = new Person();
                    setFields(resultSet, person);
                    people.add(person);
                    people.forEach(System.out::println);
                }
                return people;
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();

        }
        return Collections.emptyList();
    }

    public void sortByLastName() {
        List<Person> personList = getAllPerson();
        personList.stream()
                .map(Person::getLastName)
                .sorted()
                .forEach(System.out::println);
    }

    public void serializePerson(Role role) {
        List<Person> personList = getAllPerson();
        FilePath filePath = getPath(role);
        List<Person> newList = splitListOfPerson(personList, role);
        Serializer.serialize(newList, filePath);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserializePerson(Role role) {
        FilePath filePath = getPath(role);
        Object deserialize = Serializer.deserialize(filePath.getPath());
        List<Person> list = (List<Person>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);

        personList.addAll(list);
        System.out.println(personList);
    }

    public void printLastNameOfTeachersBeforeN() {
        List<Person> personList = getAllPerson();
        personList.stream()
                .map(Person::getLastName)
                .filter(it -> (it.substring(0, 1)).compareToIgnoreCase("N") <= 0)
                .forEach(System.out::println);
    }

    public boolean checkEmailForDuplicate(String email) {
        try {
            String sql = "SELECT email FROM public.person";
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                Set<String> emails = new HashSet<>();
                while (resultSet.next()) {
                    String emailFromDataBase = resultSet.getString("email");
                    emails.add(emailFromDataBase);
                }
                if (!emails.contains(email)) {
                    return true;
                }

            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }

        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
        return false;
    }

    public void printMapKeyEmailValueName() {
        List<Person> personList = getAllPerson();
        Map<String, String> collect = personList.stream()
                .collect(Collectors.toMap(Person::getEmail, i -> (i.getLastName() + " " + i.getFirstName())));
        System.out.println(collect);
    }

    public List<String> sortEmailsOfStudents() {
        try {
            String sql = """
                    SELECT email FROM public.person
                     WHERE role = 'STUDENT'
                     ORDER BY email""";
            List<String> emails = new ArrayList<>();
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {

                final ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String email = resultSet.getString("email");
                    emails.add(email);
                }
                return emails;
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        } catch (Exception ex) {
            System.out.println("Illegal argument" + ex);
            throw new IllegalArgumentException();
        }
        return Collections.emptyList();
    }
}

