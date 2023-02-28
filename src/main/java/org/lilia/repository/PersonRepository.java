package org.lilia.repository;

import org.lilia.model.Person;
import org.lilia.model.Role;

import java.util.*;

public class PersonRepository {
    private final List<Person> data = new ArrayList<>();

    public void add(Person person) {
        data.add(person);
    }

    public void remove(Person person) {
        data.remove(person);
    }

    public Optional<Person> getById(int id) {
        for (Person person : data) {
            if (person.getId() == id) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public void getByCourseId(int courseId, Role role) {
        for (Person person : data) {
            if (person.getCourseId() == courseId & person.getRole().equals(role)) {
                System.out.println(person);
            }
        }
        System.out.println("no objects to display");
    }

    public Optional<Person> getByLastName(String personLastName) {
        for (Person person: data){
            if (person.getLastName().equals(personLastName)){
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public Optional<Role> getRole(int choiceRole) {
        if (choiceRole == 1){
            return Optional.of(Role.TEACHER);
        }if(choiceRole == 2) {
            return Optional.of(Role.STUDENT);
        }
        return Optional.empty();
    }

    public void sortByLastName() {
        data.sort(new Person.sortByLastName());
    }
}
