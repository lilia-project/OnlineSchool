package org.lilia.service;

import org.lilia.models.Person;
import org.lilia.repository.TeacherRepository;

public class PersonService {
    private final TeacherRepository teacherRepository;

    public PersonService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Person createPerson(int courseId, String firstName, String lastName, String phone, String email) {
        Person teacher = Person.createTeacher(courseId, firstName, lastName, phone, email);
        teacherRepository.add(teacher);
        System.out.println("\nthe teacher has been created:\n" + teacher);
        return teacher;
    }

    public int[] getAllTeacherIds() {
        Person[] teacher = teacherRepository.getAll();
        int[] allTeacherIds = new int[teacher.length];
        for (int i = 0; i < teacher.length; i++) {
            allTeacherIds[i] = teacher[i].getId();
        }
        return allTeacherIds;
    }
}
