package org.lilia.service;

import org.lilia.models.Model;
import org.lilia.models.Person;
import org.lilia.repository.TeacherRepository;

public class PersonService {
    private final TeacherRepository teacherRepository;

    public PersonService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Person createPerson(int courseId, String name) {
        Person teacher = Person.createTeacher(courseId, name);
        teacherRepository.add(teacher);
        System.out.println("the teacher has been created:\n" + teacher);
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
