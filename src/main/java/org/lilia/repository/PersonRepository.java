package org.lilia.repository;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.model.Lecture;
import org.lilia.model.Person;
import org.lilia.model.Role;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;

import java.util.*;

public class PersonRepository {
    private final List<Person> list = new ArrayList<>();

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
        System.out.println("no objects to display");
    }

    public Optional<Person> getByLastName(String personLastName) {
        for (Person person : list) {
            if (person.getLastName().equals(personLastName)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public Optional<Role> getRole(int choiceRole) {
        if (choiceRole == 1) {
            return Optional.of(Role.TEACHER);
        }
        if (choiceRole == 2) {
            return Optional.of(Role.STUDENT);
        }
        return Optional.empty();
    }

    public void sortByLastName() {
        list.sort(new Person.sortByLastName());
    }

    public void serializePerson(Role role) {
        FilePath filePath = getPath(role);
        List<Person> newList = splitListOfPerson(list, role);
        Serializer.serialize(newList, filePath);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
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
        List<Person> splitList = new ArrayList<>();
        for (Person person : data) {
            if (person.getRole() == role) {
                splitList.add(person);
            }
        }
        return Optional.of(splitList).orElse(Collections.emptyList());
    }

    public void deserializePerson(Role role) {
        FilePath filePath = getPath(role);
        Object deserialize = Serializer.deserialize(filePath.getPath());
        List<Person> personList = (List<Person>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);

        for (Person person: personList){
            savePerson(person);
        }
    }

    private void savePerson(Person person) {
        if (!list.contains(person.getId())) {

            if (!list.contains(person.getLastName())) {
                list.add(person);
            }

        }
    }
}

