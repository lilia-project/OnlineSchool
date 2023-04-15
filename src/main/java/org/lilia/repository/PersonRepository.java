package org.lilia.repository;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.model.Person;
import org.lilia.model.Role;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PersonRepository {
    private final List<Person> list = new ArrayList<>();

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
        List<Person> splitList = new ArrayList<>();
        for (Person person : data) {
            if (person.getRole() == role) {
                splitList.add(person);
            }
        }
        return Optional.of(splitList).orElse(Collections.emptyList());
    }

    public void add(Person person) {
        list.add(person);
    }

    public void remove(Person person) {
        list.remove(person);
    }

    public Optional<Person> getById(int id) {
        for (Person person : list) {
            if (person.getId() == id) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public void getByCourseId(int courseId, Role role) {
        for (Person person : list) {
            if (person.getCourseId() == courseId & person.getRole().equals(role)) {
                System.out.println(person);
            }
        }
    }

    public Optional<Person> getByLastName(String personLastName) {
        for (Person person : list) {
            if (person.getLastName().equals(personLastName)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public void sortByLastName() {
        list.stream()
                .map(Person::getLastName)
                .sorted()
                .forEach(System.out::println);
    }

    public void serializePerson(Role role) {
        FilePath filePath = getPath(role);
        List<Person> newList = splitListOfPerson(list, role);
        Serializer.serialize(newList, filePath);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserializePerson(Role role) {
        FilePath filePath = getPath(role);
        Object deserialize = Serializer.deserialize(filePath.getPath());
        List<Person> list = (List<Person>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);

        for (Person person : list) {
            savePerson(person);
        }
    }

    private void savePerson(Person person) {
        for (Person currentPerson : list) {
            if (currentPerson.getId() == person.getId()) {
                ConsoleUtils.print(person.getId() + " - this id already exists");
                break;
            }
        }
        list.add(person);
    }

    public void lastNameOfTeachersBeforeN() {
        list.stream()
                .map(Person::getLastName)
                .filter(it -> (it.substring(0, 1)).compareToIgnoreCase("N") <= 0)
                .forEach(System.out::println);
    }
}

