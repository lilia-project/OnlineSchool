package org.lilia.repository;

import org.lilia.model.Person;
import org.lilia.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public int size() {
        return list.size();
    }

    public void getAllTeachers() {
        for (Person person : list) {
            if (person.getRole() == Role.TEACHER) {
                System.out.println(person);
            }
        }
    }

    public void getAllStudents() {
        for (Person person : list) {
            if (person.getRole() == Role.STUDENT) {
                System.out.println(person);
            }
        }
    }

    public Optional<Person> getBiCourseId(int courseId, Role role) {
        for (Person person : list) {
            if (person.getCourseId() == courseId) {
            }
            return Optional.of(person);
        }
        return Optional.empty();
    }

    public Optional<Person> getBiLectureId(int lectureId, Role role) {
        for (Person person : list) {
            if (person.getLectureId() == lectureId) {
            }
            return Optional.of(person);
        }
        return Optional.empty();
    }
}
