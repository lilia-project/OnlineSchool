package org.lilia.service;

import org.lilia.models.Model;
import org.lilia.models.Person;
import org.lilia.repository.GeneralRepository;

public class PersonService {
    public PersonService(GeneralRepository<Person> teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    private final GeneralRepository<Person> teacherRepository;

    public Person createPerson(int courseId, String firstName, String lastName, String phone, String email) {
        Person teacher = Person.createTeacher(courseId, firstName, lastName, phone, email);
        teacherRepository.add(teacher);
        System.out.println("\nthe teacher has been created:\n" + teacher);
        return teacher;
    }

    public Person createPerson(int courseId, String firstName, String lastName) {
        Person teacher = Person.createTeacher(courseId, firstName, lastName);
        teacherRepository.add(teacher);
        System.out.println("\nthe teacher has been created:\n" + teacher);
        return teacher;
    }

    public int[] getAllTeacherIds() {
        Model[] teacher = teacherRepository.getAll();
        int[] allTeacherIds = new int[teacher.length];
        for (int i = 0; i < teacher.length; i++) {
            allTeacherIds[i] = teacher[i].getId();
        }
        return allTeacherIds;
    }
}
