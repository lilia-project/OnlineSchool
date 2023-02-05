package org.lilia.repository;

import org.lilia.model.Person;
import org.lilia.model.Role;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class PersonRepository {
    private final SortedSet<Person> data = new TreeSet<>();

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

    public int size() {
        return data.size();
    }

    public void getAllTeachers() {
        for (Person person : data) {
            if (person.getRole() == Role.TEACHER) {
                System.out.println(person);
            }
        }
    }

    public void getAllStudents() {
        for (Person person : data) {
            if (person.getRole() == Role.STUDENT) {
                System.out.println(person);
            }
        }
    }

    public Optional<Person> getByCourseId(int courseId, Role role) {
        for (Person person : data) {
            if (person.getCourseId() == courseId) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public Optional<Person> getByLectureId(int lectureId, Role role) {
        for (Person person : data) {
            if (person.getLectureId() == lectureId) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }
}
